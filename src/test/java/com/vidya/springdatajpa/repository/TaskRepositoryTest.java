package com.vidya.springdatajpa.repository;

import com.vidya.springdatajpa.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void saveMethod() {
        Task task = new Task();
        task.setTaskName("complete JPA course");
        task.setTaskDesc("Studying for Java certification");
        task.setDueDate(LocalDateTime.of(2024, 3, 10, 12, 0, 0));

        Task savedObject = taskRepository.save(task);
        assertNotNull(savedObject.getId(), "Saved task should have an id");

        // Additional assertion if necessary
        Optional<Task> retrievedTask = taskRepository.findById(savedObject.getId());
        assertTrue(retrievedTask.isPresent(), "Saved task should be retrievable");
    }

    @Test
    void updateMethod() {
        // Assuming a task with id 1 exists for this example
        Task taskToUpdate = taskRepository.findById(1L).orElseThrow(() -> new IllegalStateException("Task not found"));

        taskToUpdate.setTaskName("updated JPA course");
        taskToUpdate.setTaskDesc("Studying for Java certification with updates");
        taskToUpdate.setDueDate(LocalDateTime.of(2024, 3, 12, 12, 0, 0));

        Task updatedObject = taskRepository.save(taskToUpdate);
        assertNotNull(updatedObject, "Updated task should not be null");

        // Additional assertion if necessary
        assertEquals("updated JPA course", updatedObject.getTaskName(), "Task name should be updated");
    }

    @Test
    void deleteMethod() {
        // Assuming a task with id 1 exists for this example
        Task taskToDelete = taskRepository.findById(1L).orElseThrow(() -> new IllegalStateException("Task not found"));

        taskRepository.delete(taskToDelete);

        // Additional assertion if necessary
        assertFalse(taskRepository.existsById(1L), "Task should be deleted");
    }

    @Test
    void readMethod() {
        // Assuming a task with id 1 exists for this example
        Optional<Task> taskOptional = taskRepository.findById(2L);

        assertTrue(taskOptional.isPresent(), "Task should be present");

        Task task = taskOptional.get();

        // Additional assertion if necessary
        assertEquals("complete JPA course", task.getTaskName(), "Task name should match");
    }

    @Test
    void saveAllMethod() {
        Task task1 = new Task();
        task1.setTaskName("Task 1");
        task1.setTaskDesc("Description 1");
        task1.setDueDate(LocalDateTime.of(2024, 3, 10, 12, 0, 0));

        Task task2 = new Task();
        task2.setTaskName("Task 2");
        task2.setTaskDesc("Description 2");
        task2.setDueDate(LocalDateTime.of(2024, 3, 11, 12, 0, 0));

        List<Task> tasksToSave = List.of(task1, task2);

        List<Task> savedTasks = taskRepository.saveAll(tasksToSave);
        assertNotNull(savedTasks, "Saved tasks should not be null");
        assertEquals(2, savedTasks.size(), "Two tasks should be saved");

        // Additional assertion if necessary
        List<Task> allTasks = taskRepository.findAll();
        assertTrue(allTasks.containsAll(savedTasks), "All saved tasks should be in the database");
    }

    @Test
    void deleteAllMethod() {
        // Assuming there are tasks in the database
        taskRepository.deleteAll();

        // Additional assertion if necessary
        List<Task> allTasks = taskRepository.findAll();
        assertTrue(allTasks.isEmpty(), "Database should be empty after deleting all tasks");
    }
}

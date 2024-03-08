package com.vidya.springdatajpa.repository;

import com.vidya.springdatajpa.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {


}

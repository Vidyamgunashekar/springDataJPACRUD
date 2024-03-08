package com.vidya.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;

@Table(name="task")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id",unique = true,nullable = false)
    private Long id;

    @Column(nullable = false)
    private String taskName;

    @Column(name = "task_description",nullable = false)
    private String taskDesc;

    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    private LocalDateTime taskCreated;

    @Column(columnDefinition = "LocalDateTime",nullable = true,updatable = true)
    private LocalDateTime taskCompleted;

    @Column(nullable = false,updatable = false)
    private LocalDateTime dueDate;

}

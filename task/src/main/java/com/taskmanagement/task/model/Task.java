package com.taskmanagement.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tilte;
    private String description;
    private String image;
    private Long assignedUserId;
    private List<String> tags = new  ArrayList<>();
    private String status;
    private LocalDateTime dl;
    private LocalDateTime creatAt;
}

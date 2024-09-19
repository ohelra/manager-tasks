package com.taskmanager.submissionTask.service;

import com.taskmanager.submissionTask.model.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "SUBMiSSION-SERVICE", url = "http://localhost:8082")
public interface TaskService {
    @GetMapping("/api/tasks/{id}")
    public TaskDTO getTaskById(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception;
    @PutMapping("/api/tasks/task-complete/{id}")
    public TaskDTO getCompleteTask(@PathVariable Long id) throws Exception;
}

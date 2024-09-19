package com.taskmanagement.task.service;

import com.taskmanagement.task.model.Task;

import java.util.List;

public interface TaskService {
    public Task createTask(Task task, String req) throws Exception;
    public Task getTaskById(Long id) throws Exception;
    public List<Task> getALlTask(String status);
    public Task updateTask(Long id, Task update, Long userId) throws Exception;
    public void deleteTask(Long id) throws Exception;
    public Task assignedToUser(Long userId, Long taskId) throws Exception;
    public List<Task> assignedUsersTask(Long userId, String status);
    public Task completeTask(Long taskId) throws Exception;
}

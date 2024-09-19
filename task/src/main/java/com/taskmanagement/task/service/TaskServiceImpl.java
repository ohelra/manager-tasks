package com.taskmanagement.task.service;

import com.taskmanagement.task.model.Task;
import com.taskmanagement.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task, String req) throws Exception {
        if (!req.equals("ROLE_ADMIN")){
            throw new Exception("Admin able to create task");
        }
        task.setStatus("PENDING");
        task.setCreatAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) throws Exception {
        return taskRepository.findById(id).orElseThrow(()->new Exception("Task not found " + id));
    }

    @Override
    public List<Task> getALlTask(String status) {
        List<Task> alltask = taskRepository.findAll();
        List<Task> filterTask = alltask.stream().
                filter(task -> status == null || task.getStatus().equalsIgnoreCase(status.toString()))
                .collect(Collectors.toList());
        return filterTask;
    }

    @Override
    public Task updateTask(Long id, Task update, Long userId) throws Exception {
        Task existing = getTaskById(id);
        if (update.getTilte() != null){
            existing.setTilte(update.getTilte());
        }
        else if (update.getImage() != null){
            existing.setImage(update.getImage());
        }
        else if (update.getDescription() != null){
            existing.setDescription(update.getDescription());
        }
        else if (update.getStatus() != null){
            existing.setStatus(update.getStatus());
        }
        else {
            existing.setDl(update.getDl());
        }
        return taskRepository.save(existing);
    }

    @Override
    public void deleteTask(Long id) throws Exception{
        getTaskById(id);
        taskRepository.deleteById(id);
    }

    @Override
    public Task assignedToUser(Long userId, Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setAssignedUserId(userId);
        task.setStatus("DONE");
        return taskRepository.save(task);
    }

    @Override
    public List<Task> assignedUsersTask(Long userId, String status) {
        List<Task> allTask = taskRepository.findByAssignedUserId(userId);
        List<Task> filterTask = allTask.stream().
                filter(task -> status == null || task.getStatus().equalsIgnoreCase(status.toString()))
                .collect(Collectors.toList());
        return filterTask;
    }

    @Override
    public Task completeTask(Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setStatus("DONE");
        return taskRepository.save(task);
    }
}

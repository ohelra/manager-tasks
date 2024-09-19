package com.taskmanagement.task.controller;

import com.taskmanagement.task.model.Task;
import com.taskmanagement.task.model.UserDTO;
import com.taskmanagement.task.service.TaskService;
import com.taskmanagement.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<Task> createTask(@RequestBody Task task,
                                           @RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO userDTO = userService.getUserProfile(jwt);
        Task task1 = taskService.createTask(task, userDTO.getRole());
        return new ResponseEntity<>(task1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id,
                                            @RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO userDTO = userService.getUserProfile(jwt);
        Task task1 = taskService.getTaskById(id);
        return new ResponseEntity<>(task1, HttpStatus.OK);
    }

    @GetMapping("/assigned")
    public ResponseEntity<List<Task>> getAssignedUserTask(@RequestParam(required = false) String status,
                                            @RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO userDTO = userService.getUserProfile(jwt);
        List<Task> task1 = taskService.assignedUsersTask(userDTO.getId(), status);
        return new ResponseEntity<>(task1, HttpStatus.OK);
    }

    @GetMapping("/list/{status}")
    public ResponseEntity<List<Task>> getAllTask(@PathVariable String status,
                                                 @RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO userDTO = userService.getUserProfile(jwt);
        List<Task> task1 = taskService.getALlTask(status);
        return new ResponseEntity<>(task1, HttpStatus.OK);
    }

    @PutMapping("/user/assigned/{id}/{userId}")
    public ResponseEntity<Task> assignedTaskToUsers(@PathVariable Long id, @PathVariable Long userId,
                                            @RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO userDTO = userService.getUserProfile(jwt);
        Task task1 = taskService.assignedToUser(userId, id);
        return new ResponseEntity<>(task1, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task req,
                                            @RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO userDTO = userService.getUserProfile(jwt);
        Task task1 = taskService.updateTask(id,req,userDTO.getId());
        return new ResponseEntity<>(task1, HttpStatus.OK);
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<Task> getCompleteTask(@PathVariable Long id) throws Exception{
        Task task1 = taskService.completeTask(id);
        return new ResponseEntity<>(task1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) throws Exception{
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

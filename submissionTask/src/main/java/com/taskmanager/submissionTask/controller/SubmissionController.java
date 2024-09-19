package com.taskmanager.submissionTask.controller;

import com.taskmanager.submissionTask.model.Submission;
import com.taskmanager.submissionTask.model.UserDTO;
import com.taskmanager.submissionTask.service.SubmissionService;
import com.taskmanager.submissionTask.service.TaskService;
import com.taskmanager.submissionTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @PostMapping("/submit")
    public ResponseEntity<Submission> submitTask(@RequestParam Long taskId, @RequestParam String githubLink,
                                                 @RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO userDTO = userService.getUserProfile(jwt);
        Submission submission = submissionService.submitTask(taskId, githubLink, userDTO.getId(), jwt);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping("/sub/{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id,
                                                        @RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO userDTO = userService.getUserProfile(jwt);
        Submission submission = submissionService.getTaskSubmissionById(id);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<Submission>> getAllSubmission(@RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO userDTO = userService.getUserProfile(jwt);
        List<Submission> submission = submissionService.getAllTaskSubmissions();
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getSubmissionTaskById(@PathVariable Long taskId,
                                                                  @RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO userDTO = userService.getUserProfile(jwt);
        List<Submission> submission = submissionService.getTaskSubmissionByTaskId(taskId);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<Submission> acceptDeclineSubmission(@PathVariable Long id, @RequestParam("status") String status,
                                                              @RequestHeader("Authorization") String jwt) throws Exception{
        UserDTO userDTO = userService.getUserProfile(jwt);
        Submission submission = submissionService.acceptDeclineSubmission(id,status);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }
}

package com.taskmanager.submissionTask.service;

import com.taskmanager.submissionTask.model.Submission;
import com.taskmanager.submissionTask.model.TaskDTO;
import com.taskmanager.submissionTask.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService{
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private TaskService taskService;
    @Override
    public Submission submitTask(Long taskId, String githubLink, Long userId, String jwt) throws Exception {
        TaskDTO taskDTO = taskService.getTaskById(taskId, jwt);
        if (taskDTO != null){
            Submission submission = new Submission();
            submission.setTaskId(taskId);
            submission.setUserId(userId);
            submission.setGithubLink(githubLink);
            submission.setSubmissionTime(LocalDateTime.now());
            return submissionRepository.save(submission);
        }else {
            throw new Exception("Task not found with id : "+taskId);
        }
    }

    @Override
    public Submission getTaskSubmissionById(Long submissionId) throws Exception {
        return submissionRepository.findById(submissionId)
                .orElseThrow(()->new Exception("Task submission not found with id : "+submissionId));
    }

    @Override
    public List<Submission> getAllTaskSubmissions() {
        return submissionRepository.findAll();
    }

    @Override
    public List<Submission> getTaskSubmissionByTaskId(Long taskId) {
        return submissionRepository.findByTaskId(taskId);
    }

    @Override
    public Submission acceptDeclineSubmission(Long id, String status) throws Exception {
        Submission submission = getTaskSubmissionById(id);
        submission.setStatus(status);
        if (status.equals("ACCEPT")){
            taskService.getCompleteTask(submission.getTaskId());
        }
        return submissionRepository.save(submission);
    }
}

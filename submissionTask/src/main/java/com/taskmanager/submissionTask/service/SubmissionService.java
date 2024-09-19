package com.taskmanager.submissionTask.service;

import com.taskmanager.submissionTask.model.Submission;

import java.util.List;

public interface SubmissionService {
    public Submission submitTask(Long taskId, String githubLink, Long userId, String jwt) throws Exception;
    public Submission getTaskSubmissionById(Long submissionId) throws Exception;
    public List<Submission> getAllTaskSubmissions();
    public List<Submission> getTaskSubmissionByTaskId(Long taskId);
    public Submission acceptDeclineSubmission(Long id, String status) throws Exception;
}

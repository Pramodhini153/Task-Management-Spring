package com.taskmang.service;

import java.util.List;

import com.taskmang.model.Submission;


public interface SubmissionService {
	
	
Submission submitTask(Long taskId,String githubLink,Long userId,String jwt)throws Exception;
	
	Submission getTaskSubmissionById(Long submissionID)throws Exception;
	
	List<Submission> getAllTaskSubmissions();
	
	List<Submission> getTaskSubmissionsByTaskId(Long taskId)throws Exception;
	
	Submission acceptDeclineSubmission(Long id,String status)throws Exception;
}

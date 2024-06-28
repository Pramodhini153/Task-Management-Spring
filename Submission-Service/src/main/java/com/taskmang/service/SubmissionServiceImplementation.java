package com.taskmang.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmang.model.Submission;
import com.taskmang.model.TaskDto;
import com.taskmang.repository.SubmissionRepository;


@Service
public class SubmissionServiceImplementation implements SubmissionService{
	
	@Autowired
	private SubmissionRepository submissionRepository;
	
	
	@Autowired
	private TaskService taskService;
	
	@Override
	public Submission submitTask(Long taskId, String githubLink, Long userId,String jwt) throws Exception {
		TaskDto tasks = taskService.getTaskById(taskId, jwt);
		if(tasks!=null) {
			Submission submission = new Submission();
			submission.setTaskId(taskId);
			submission.setUserId(userId);
			submission.setGithubLink(githubLink);
			submission.setSubmissionTime(LocalDateTime.now());
			return submissionRepository.save(submission);
		}
		throw new Exception("Task not found with id -"+taskId);
		
	}

	@Override
	public Submission getTaskSubmissionById(Long submissionID) throws Exception {
		
		return submissionRepository.findById(submissionID).orElseThrow(()-> new Exception("Task submission not found with id"+submissionID));
	}

	@Override
	public List<Submission> getAllTaskSubmissions() {
		
		return submissionRepository.findAll();
	}

	@Override
	public List<Submission> getTaskSubmissionsByTaskId(Long taskId) {
		
		return submissionRepository.findByTaskId(taskId);
	}

	@Override
	public Submission acceptDeclineSubmission(Long id, String status) throws Exception {
		Submission submission = getTaskSubmissionById(id);
		submission.setStatus(status);
		if(status.equals("ACCEPT")) {
			taskService.completeTask(submission.getTaskId());
		}
		
		return submissionRepository.save(submission);
	}
	
	
	
}

























//@Service
//public class SubmissionServiceImplementation implements SubmissionService {
//
//	@Autowired
//	private SubmissionRepository repo;
//	
//	@Autowired
//	private TaskService taskservice;
//	
//	@Override
//	public Submission submitTask(Long taskId,String githubLink,Long userId,String jwt)throws Exception{
//		
//		TaskDto task=taskservice.getTaskById(taskId,jwt);
//		if(task!=null) {
//			Submission submission=new Submission();
//			submission.setTaskId(taskId);
//			submission.setUserId(userId);
//			submission.setGithubLink(githubLink);
//			submission.setSubmissionTime(LocalDateTime.now());
//			return repo.save(submission);
//		}
//		throw new Exception("Task not found with id: "+taskId);
//	}
//	
//	@Override
//	public Submission getTaskSubmissionById(Long submissionId)throws Exception{
//		
//		return repo.findById(submissionId).orElseThrow(()->new Exception("Task Submission not found with id: "+submissionId));
//	}
//	
//	@Override
//	public List<Submission> getAllTaskSubmissions(){
//		
//		return repo.findAll();
//	}
//	
//	@Override
//	public List<Submission> getTaskSubmissionsByTaskId(Long TaskId)throws Exception{
//		
//		return repo.findByTaskId(TaskId);
//	}
//	
//	@Override
//	public Submission acceptDeclineSubmission(Long id,String status)throws Exception{
//		Submission submission=getTaskSubmissionById(id);
//		submission.setStatus(status);
//		if(status.equals("ACCEPT")) {
//			taskservice.completeTask(submission.getTaskId());
//		}
//		
//		
//		return repo.save(submission);
//	}
//}

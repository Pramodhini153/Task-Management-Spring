package com.taskmang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmang.model.Task;
import com.taskmang.model.TaskStatus;

public interface TaskService {

	Task createTask(Task task,String requesterRole)throws Exception;
	Task getTaskById(Long id)throws Exception;
	List<Task> getAllTasks(TaskStatus status);
	Task updateTask(Long id,Task updatedTask,Long userId)throws Exception;
	void deleteTask(Long id) throws Exception;
	Task assignedToUser(Long userId,Long taskId)throws Exception;
	List<Task> assignedUsersTask(Long userId,TaskStatus status);
	Task completeTask(Long taskId)throws Exception;
	
}

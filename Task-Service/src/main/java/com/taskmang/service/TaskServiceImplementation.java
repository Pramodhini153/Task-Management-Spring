package com.taskmang.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.taskmang.model.Task;
import com.taskmang.model.TaskStatus;
import com.taskmang.repository.TaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;


@Service
public class TaskServiceImplementation implements TaskService{
	
	@Autowired
	private TaskRepository repo;
	
	
	@Override
	public Task createTask(Task task, String requesterRole) throws Exception {
		if(!requesterRole.equals(("ROLE_ADMIN"))) {
			throw new Exception("only admin can create task");
		}
		task.setStatus(TaskStatus.PENDING);
		task.setCreatedAt(LocalDateTime.now());
		return repo.save(task);
	}

	@Override
	public Task getTaskById(Long id) throws Exception {
		
		return repo.findById(id).orElseThrow(()->new Exception("task not found with id "+id));
	}

	@Override
	public List<Task> getAllTasks(TaskStatus status) {
		List<Task> allTask = repo.findAll();
		
		List<Task> filteredTasks= allTask.stream().filter(
				task -> status==null || task.getStatus().name().equalsIgnoreCase(status.toString())
				).collect(Collectors.toList());
		return filteredTasks;
	}

	@Override
	public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception {
		
		Task existingTask = getTaskById(id);
		
		if(updatedTask.getTitle()!=null) {
			existingTask.setTitle(updatedTask.getTitle());
		}
		if(updatedTask.getImage()!=null) {
			existingTask.setImage(updatedTask.getImage());
		}
		if(updatedTask.getDescription()!=null) {
			existingTask.setDescription(updatedTask.getDescription());
		}
		if(updatedTask.getStatus()!=null) {
			existingTask.setStatus(updatedTask.getStatus());
		}
		if(updatedTask.getDeadline()!=null) {
			existingTask.setDeadline(updatedTask.getDeadline());
		}
		return repo.save(existingTask);
	}

	@Override
	public void deleteTask(Long id) throws Exception{
		
		getTaskById(id);
		
		repo.deleteById(id);
		
	}

	@Override
	public Task assignedToUser(Long userId, Long taskId) throws Exception {
		Task task = getTaskById(taskId);
		task.setAssignedUserId(userId);
		task.setStatus(TaskStatus.DONE);
		return repo.save(task);
	}

	@Override
	public List<Task> assignedUsersTask(Long userId, TaskStatus status) {
		
		List<Task> allTasks = repo.findByAssignedUserId(userId);
		
		List<Task> filteredTasks= allTasks.stream().filter(
				task -> status==null || task.getStatus().name().equalsIgnoreCase(status.toString())
				).collect(Collectors.toList());
		return filteredTasks;
		
	}

	@Override
	public Task completeTask(Long taskId) throws Exception {
		
		Task task = getTaskById(taskId);
		task.setStatus(TaskStatus.DONE);
		
		return repo.save(task);
	}	
}












//@Service
//public class TaskServiceImplementation implements TaskService{
//
//	@Autowired
//	private TaskRepository repo;
//	
//	@Override
//	public Task createTask(Task task, String requesterRole) throws Exception {
//
//		if(!requesterRole.equals("ROLE_ADMIN")) {
//			throw new Exception("Only admin can create Task");
//		}
//		task.setStatus(TaskStatus.PENDING);
//		task.setCreatedAt(LocalDateTime.now());
//		System.out.println("task created");
//		return repo.save(task);
//	}
//
//	@Override
//	public Task getTaskById(Long id) throws Exception {
//		
//		
//		return repo.findById(id).orElseThrow(()->new Exception("Task not found with id"+id));
//	}
//
//	@Override
//	public List<Task> getAllTasks(TaskStatus status) {
//		
//		List<Task> allTask=repo.findAll();
//		List<Task> filteredTasks=allTask.stream().filter(
//				task->status==null || task.getStatus().name().equalsIgnoreCase(status.toString())
//				).collect(Collectors.toList());
//
//		return filteredTasks;
//	}
//
//	@Override
//	public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception {
//		
//		Task existingTask=getTaskById(id);
//		if(updatedTask.getTitle()!=null) {
//			existingTask.setTitle(updatedTask.getTitle());
//		}
//		if(updatedTask.getImage()!=null) {
//			existingTask.setImage(updatedTask.getImage());
//		}
//		if(updatedTask.getDescription()!=null) {
//			existingTask.setDescription(updatedTask.getDescription());
//		}
//		if(updatedTask.getStatus()!=null) {
//			existingTask.setStatus(updatedTask.getStatus());
//		}
//		if(updatedTask.getDeadline()!=null) {
//			existingTask.setDeadline(updatedTask.getDeadline());
//		}
//		return repo.save(existingTask);
//	}
//
//	@Override
//	public void deleteTask(Long id) throws Exception{
//		getTaskById(id);
//		repo.deleteById(id);
//		
//	}
//
//	@Override
//	public Task assignedToUser(Long userId, Long taskId) throws Exception {
//		
//		Task task=getTaskById(taskId);
//		task.setAssignedUserId(userId);
//		task.setStatus(TaskStatus.DONE);
//		return repo.save(task);
//	}
//
//	@Override
//	public List<Task> assignedUsersTask(Long userId, TaskStatus status) {
//		
//		List<Task> allTask=repo.findByAssignedUserId(userId);
//		List<Task> filteredTasks=allTask.stream().filter(
//				task->status==null || task.getStatus().name().equalsIgnoreCase(status.toString())
//				).collect(Collectors.toList());
//		return filteredTasks;
//	}
//
//	@Override
//	public Task completeTask(Long taskId) throws Exception {
//		Task task=getTaskById(taskId);
//		task.setStatus(TaskStatus.DONE);
//		
//		return repo.save(task);
//	}
//
//	
//}

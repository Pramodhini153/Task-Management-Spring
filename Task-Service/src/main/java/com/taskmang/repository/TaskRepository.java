package com.taskmang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmang.model.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{

	public List<Task> findByAssignedUserId(Long userId);
}

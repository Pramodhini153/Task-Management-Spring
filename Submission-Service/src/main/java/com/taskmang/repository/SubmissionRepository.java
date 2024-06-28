package com.taskmang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmang.model.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Long>{

	List<Submission> findByTaskId(Long taskId);
}
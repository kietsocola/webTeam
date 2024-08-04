package com.myProject.webteam.respositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myProject.webteam.models.Task;

public interface TaskResponsitory extends JpaRepository<Task, Integer>{
	@Query("SELECT c FROM Task c WHERE c.project = :project")
	List<Task> findByIdProject(@Param("project") int project);
	List<Task> findByDateEndBeforeAndStatusNot(LocalDateTime dateTime, int status);
}

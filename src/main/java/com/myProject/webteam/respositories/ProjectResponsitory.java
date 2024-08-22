package com.myProject.webteam.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.webteam.models.Project;

public interface ProjectResponsitory extends JpaRepository<Project, Integer> {
	List<Project> findByUsers_Id(int userId);
}

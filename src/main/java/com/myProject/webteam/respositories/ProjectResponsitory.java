package com.myProject.webteam.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.webteam.models.Project;

public interface ProjectResponsitory extends JpaRepository<Project, Integer> {

}

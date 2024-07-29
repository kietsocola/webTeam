package com.myProject.webteam.services;

import java.util.List;

import com.myProject.webteam.models.Project;

public interface ProjectService {
	List<Project> getListProject();
	Project getProjectById(int id);
	Project saveProject(String name);
}

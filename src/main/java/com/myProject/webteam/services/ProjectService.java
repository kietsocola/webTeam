package com.myProject.webteam.services;

import java.util.List;

import com.myProject.webteam.models.Project;
import com.myProject.webteam.models.User;

public interface ProjectService {
	List<Project> getListProject();
	Project getProjectById(int id);
	Project saveProject(String name);
	Project saveProject(Project pro);
}

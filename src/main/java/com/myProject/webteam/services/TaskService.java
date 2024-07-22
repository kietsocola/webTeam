package com.myProject.webteam.services;

import java.util.List;

import com.myProject.webteam.models.Task;

public interface TaskService {
	List<Task> getTaskByIdProject(int idProject);
}

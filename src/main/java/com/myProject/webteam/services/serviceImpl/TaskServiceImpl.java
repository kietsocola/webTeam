package com.myProject.webteam.services.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.webteam.models.Task;
import com.myProject.webteam.respositories.TaskResponsitory;
import com.myProject.webteam.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

	private TaskResponsitory taskRepo;
	@Autowired
	public TaskServiceImpl(TaskResponsitory taskRepo) {
		this.taskRepo = taskRepo;
	}
	@Override
	public List<Task> getTaskByIdProject(int idProject) {
		return taskRepo.findByIdProject(idProject);
	}

}

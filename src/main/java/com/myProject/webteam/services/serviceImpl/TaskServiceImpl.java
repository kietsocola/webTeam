package com.myProject.webteam.services.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.webteam.dto.TaskDTO;
import com.myProject.webteam.mapper.TaskMapper;
import com.myProject.webteam.models.Task;
import com.myProject.webteam.respositories.TaskResponsitory;
import com.myProject.webteam.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

	private TaskResponsitory taskRepo;
	private TaskMapper taskMapper;
	@Autowired
	public TaskServiceImpl(TaskResponsitory taskRepo, TaskMapper taskMapper) {
		this.taskRepo = taskRepo;
		this.taskMapper = taskMapper;
	}
	@Override
	public List<Task> getTaskByIdProject(int idProject) {
		return taskRepo.findByIdProject(idProject);
	}
	@Override
	public Task saveTask(TaskDTO taskDto) {
		Task task = taskMapper.toTask(taskDto);
		task.setDateCreate(LocalDateTime.now());
		Task taskSaved = taskRepo.save(task);
		return taskSaved;
	}
	@Override
	public void changTaskStatus(int idTask, int status) {
		Task task = taskRepo.findById(idTask).orElseThrow(() -> new IllegalArgumentException("Invalid task ID: " + idTask));
		task.setStatus(status);
		taskRepo.save(task);
	}
	@Override
	public Optional<Task> getTaskById(int id) {
		return taskRepo.findById(id);
	}

}

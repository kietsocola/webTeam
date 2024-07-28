package com.myProject.webteam.services;

import java.util.List;
import java.util.Optional;

import com.myProject.webteam.dto.TaskDTO;
import com.myProject.webteam.models.Task;

public interface TaskService {
	List<Task> getTaskByIdProject(int idProject);
	Task saveTask(TaskDTO taskDto);
	void changTaskStatus(int idTask, int status);
	Optional<Task> getTaskById(int id);
}

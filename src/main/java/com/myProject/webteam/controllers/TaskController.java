package com.myProject.webteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.webteam.dto.TaskDTO;
import com.myProject.webteam.models.Comment;
import com.myProject.webteam.models.Task;
import com.myProject.webteam.services.TaskService;

@Controller
public class TaskController {
	private TaskService taskService;
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@PostMapping("/project/{idProject}/addTask")
	public String loadTaskOfProject(@ModelAttribute TaskDTO taskDTO, @PathVariable("idProject") int idProject) {
		Task taskSaved = taskService.saveTask(taskDTO);
		return "redirect:/project?idProject="+idProject;
	}
	
	@GetMapping("/task/changeStatus/{idProject}/{idTask}/{status}")
	public String changeStatus(@PathVariable("idProject") int idProject, @PathVariable("idTask") int idTask, @PathVariable("status") int status) {
		taskService.changTaskStatus(idTask, status);
		return "redirect:/project?idProject="+idProject;
	}
}

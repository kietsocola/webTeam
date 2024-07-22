package com.myProject.webteam.controllers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myProject.webteam.models.Project;
import com.myProject.webteam.models.Task;
import com.myProject.webteam.services.ProjectService;
import com.myProject.webteam.services.TaskService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	private ProjectService projectService;
	private TaskService taskService;
	@Autowired
	public ProjectController(ProjectService projectService, TaskService taskService) {
		this.projectService = projectService;
		this.taskService = taskService;
	}
	@GetMapping({"", "/"})
	public String home(Model model) {
		List<Project> projects = projectService.getListProject();
		model.addAttribute("projects", projects);
		List<Integer> loopList = Arrays.asList(1, 2, 3);
        model.addAttribute("loopList", loopList);
		return "board/index";
	}
	@GetMapping("/loadTasks")
	public String loadTaskOfProject(Model model, @RequestParam int idProject) {
		try {
			List<Project> projects = projectService.getListProject();
			model.addAttribute("projects", projects);
			List<Task> tasks = taskService.getTaskByIdProject(idProject);
			LocalDateTime now = LocalDateTime.now();
			tasks.forEach(task -> {
	            if (task.getDateEnd() != null) {
	                Duration duration = Duration.between(now, (Temporal) task.getDateEnd());
	                long hoursRemaining = duration.toHours();
	                task.setHoursRemaining(hoursRemaining);
	            }
	        });
			model.addAttribute("tasks", tasks);
			List<Integer> loopList = Arrays.asList(1, 2, 3);
	        model.addAttribute("loopList", loopList);
		} catch (Exception e) {
		}
		return "board/index";
	}
}

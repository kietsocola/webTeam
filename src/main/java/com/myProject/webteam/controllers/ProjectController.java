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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myProject.webteam.models.Project;
import com.myProject.webteam.models.Task;
import com.myProject.webteam.services.CategoryService;
import com.myProject.webteam.services.ProjectService;
import com.myProject.webteam.services.TaskService;

@Controller
public class ProjectController {
	private ProjectService projectService;
	private CategoryService categoryService;
	@Autowired
	public ProjectController(ProjectService projectService, CategoryService categoryService) {
		this.projectService = projectService;
		this.categoryService = categoryService;
	}
	@GetMapping("/project")
	public String home(Model model) {
		List<Project> projects = projectService.getListProject();
		model.addAttribute("projects", projects);
		return "board/index";
	}
	@GetMapping("/project/{idProject}")
	public String detailProject(Model model, @PathVariable("idProject") int idProject) {
		List<Project> projects = projectService.getListProject();
		model.addAttribute("projects", projects);
		Project project = projectService.getProjectById(idProject);
		LocalDateTime now = LocalDateTime.now();
		project.getTasks().forEach(task -> {
            if (task.getDateEnd() != null) {
                Duration duration = Duration.between(now, (Temporal) task.getDateEnd());
                long hoursRemaining = duration.toHours();
                task.setHoursRemaining(hoursRemaining);
            }
        });
		model.addAttribute("projectFindById", project);
		model.addAttribute("userOfProject", project.getUsers());
		model.addAttribute("categoryTask", categoryService.getListCategory());
		List<Integer> loopList = Arrays.asList(1, 2, 3);
        model.addAttribute("loopList", loopList);
		return "board/index";
	}
}

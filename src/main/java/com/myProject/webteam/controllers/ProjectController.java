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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myProject.webteam.dto.TaskDTO;
import com.myProject.webteam.models.Project;
import com.myProject.webteam.models.Task;
import com.myProject.webteam.models.User;
import com.myProject.webteam.services.CategoryService;
import com.myProject.webteam.services.PointService;
import com.myProject.webteam.services.ProjectService;
import com.myProject.webteam.services.TaskService;
import com.myProject.webteam.services.UserService;

@Controller
public class ProjectController {
	private ProjectService projectService;
	private CategoryService categoryService;
	private PointService pointService;
	private UserService userService;
	@Autowired
	public ProjectController(ProjectService projectService, CategoryService categoryService, PointService pointService, UserService userService) {
		this.projectService = projectService;
		this.categoryService = categoryService;
		this.pointService = pointService;
		this.userService = userService;
	}
	@GetMapping("/project")
	public String home(Model model) {
		List<Project> projects = projectService.getListProject();
		model.addAttribute("projects", projects);
		Project project = projects.getLast();
		model.addAttribute("projectFindById", project);
		return "home/index";
	}
	@PostMapping("/addProject")
	public String addProject(Model model, @RequestParam String nameProject) {
		projectService.saveProject(nameProject);
		List<Project> projects = projectService.getListProject();
		model.addAttribute("projects", projects);
		Project project = projects.getLast();
		model.addAttribute("projectFindById", project);
		return "redirect:/project";
	}
	@GetMapping("/project/changePage")
	public String detailProject(Model model, @RequestParam("idProject") int idProject) {
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
        model.addAttribute("taskNew", new TaskDTO());
		return "project/index";
	}
	@GetMapping("/project/addUser")
	public String addUser(@RequestParam("idPro") int idProject, @RequestParam("idUser") int idUser) {
		User u = userService.getUserById(idUser);
	    Project project = projectService.getProjectById(idProject);
	    
	    if (!project.getUsers().contains(u) && u!=null) {
	        project.getUsers().add(u);
			projectService.saveProject(project);
			pointService.createPointFor_newUser(project, u);
	    }
		return "redirect:/project";
	}
}

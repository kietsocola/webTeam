package com.myProject.webteam.controllers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

import com.myProject.webteam.dto.TaskDTO;
import com.myProject.webteam.models.Project;
import com.myProject.webteam.models.ProjectRole;
import com.myProject.webteam.models.Role;
import com.myProject.webteam.models.Task;
import com.myProject.webteam.models.User;
import com.myProject.webteam.respositories.ProjectRoleResponsitory;
import com.myProject.webteam.respositories.RoleResponsitory;
import com.myProject.webteam.security.SecurityUtil;
import com.myProject.webteam.services.CategoryService;
import com.myProject.webteam.services.PointService;
import com.myProject.webteam.services.ProjectService;
import com.myProject.webteam.services.TaskService;
import com.myProject.webteam.services.UserService;
import com.myProject.webteam.services.serviceImpl.SendMailService;

@Controller
public class ProjectController {
//	@Autowired
//  private SendMailService emailService;
	@Autowired
	private ProjectRoleResponsitory projectRoleRepo;
	@Autowired
	private RoleResponsitory roleRepo;
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
		String nameLogin = SecurityUtil.getSessionUser();
        User u = userService.getUserByNameLogin(nameLogin);
		List<Project> projects = projectService.getListProjectByUserId(u.getId());
		model.addAttribute("projects", projects);
		// Kiểm tra xem danh sách projects có rỗng không
	    if (projects != null && !projects.isEmpty()) {
	        Project project = projects.get(projects.size() - 1); // Lấy dự án cuối cùng
	        model.addAttribute("projectFindById", project);
	    } else {
	        model.addAttribute("projectFindById", null); // Không có dự án nào
	    }
		return "home/index";
	}
	@PostMapping("/addProject")
	public String addProject(Model model, @RequestParam String nameProject) {
		String nameLogin = SecurityUtil.getSessionUser();
        User u = userService.getUserByNameLogin(nameLogin);
		Project p = new Project();
		p.setName(nameProject);
		List<User> userList = new ArrayList<>();
		userList.add(u);
		p.setUsers(userList);
		projectService.saveProject(p);
		List<Project> projects = projectService.getListProjectByUserId(u.getId());
		model.addAttribute("projects", projects);
		Project project = projects.getLast();
		model.addAttribute("projectFindById", project);
		ProjectRole projectRole = new ProjectRole();
		projectRole.setProject(p);
		projectRole.setUser(u);
		Role role = roleRepo.findByName("LEADER");
		projectRole.setRole(role);
		projectRoleRepo.save(projectRole);
		pointService.createPointFor_newUser(project, u);
		return "redirect:/project";
	}
	@GetMapping("/project/changePage")
	public String detailProject(Model model, @RequestParam("idProject") int idProject) {
		String nameLogin = SecurityUtil.getSessionUser();
        User u = userService.getUserByNameLogin(nameLogin);
		List<Project> projects = projectService.getListProjectByUserId(u.getId());
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
	@PostMapping("/project/addUser")
	public String addUser(@RequestParam("idPro") int idProject, @RequestParam("mailUser") String mailUser) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream()
	            .anyMatch(role -> role.getAuthority().equals("LEADER"))) {
	        // code for admin role

			
			User u = userService.getUserByEmail(mailUser);
		    Project project = projectService.getProjectById(idProject);
		    //add Role
		    ProjectRole projectRole = new ProjectRole();
			projectRole.setProject(project);
			projectRole.setUser(u);
			Role role = roleRepo.findByName("USER");
			projectRole.setRole(role);
			projectRoleRepo.save(projectRole);
			
		    System.out.println("savdsvddddd  " + u.getEmail());
//		    emailService.sendSimpleMessage(mailUser, "[WEBTEAM thông báo]", "Bạn vừa được thêm vào project "+project.getName()+" bởi "+u.getNameLogin());
		    if (!project.getUsers().contains(u) && u!=null) {
		        project.getUsers().add(u);
				projectService.saveProject(project);
				pointService.createPointFor_newUser(project, u);
//				
//				emailService.sendSimpleMessage(mailUser, "[WEBTEAM thông báo]", "Bạn vừa được thêm vào project "+project.getName()+" bởi "+u.getNameLogin());
		    }
	    } else {
	        // code for non-leader
	    	for (GrantedAuthority authority : auth.getAuthorities()) {
	            System.out.println("User has role: " + authority.getAuthority());
	        }
	    }
		
		return "redirect:/project";
	}
}

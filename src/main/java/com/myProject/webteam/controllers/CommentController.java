package com.myProject.webteam.controllers;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myProject.webteam.dto.CommentDTO;
import com.myProject.webteam.models.Comment;
import com.myProject.webteam.models.Task;
import com.myProject.webteam.models.User;
import com.myProject.webteam.respositories.CommentResponsitory;
import com.myProject.webteam.security.SecurityUtil;
import com.myProject.webteam.services.CommentService;
import com.myProject.webteam.services.TaskService;
import com.myProject.webteam.services.UserService;

@Controller
public class CommentController {
	@Autowired
	private CommentService cmtService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;

	@PostMapping("/sendComment")
	@ResponseBody
	public CommentDTO saveComment(@RequestBody CommentDTO cmtDto) {
		// Log the incoming CommentDTO
		System.out.println("Received CommentDTO: " + cmtDto);

		Task task = new Task();
		task = taskService.getTaskById(cmtDto.getIdTask())
				.orElseThrow(() -> new RuntimeException("Task not found with id: " + cmtDto.getIdTask()));
		// Log the found task
		System.out.println("Found Task: " + task);

		String nameLogin = SecurityUtil.getSessionUser();
        User user = userService.getUserByNameLogin(nameLogin);
		System.out.println("name login: " + user.getNameLogin());

		Comment comment = new Comment();
		comment.setTask(task);
		comment.setUser(user);
		comment.setContent(cmtDto.getContent());
		comment.setDateCreate(LocalDateTime.now());

		System.out.println(user.getNameLogin());

		// Log the comment before saving
		System.out.println("Saving Comment: " + comment);
		cmtDto.setNameUser(comment.getUser().getNameLogin());
		cmtDto.setDateCreate(comment.getDateCreate());
		cmtService.saveComment(comment);
		System.out.println(comment.getUser().getNameLogin());
		return cmtDto;
	}
}

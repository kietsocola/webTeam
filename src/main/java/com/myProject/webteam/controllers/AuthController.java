package com.myProject.webteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.myProject.webteam.dto.RegistrationDto;
import com.myProject.webteam.models.User;
import com.myProject.webteam.services.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
    public String loginPage(){
        return "form/login";
    }
	@GetMapping("/register")
	public String getRegesterForm(Model model) {
		RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "form/register";
	}
	@PostMapping("/register/save")
	public String register(Model model, @Valid @ModelAttribute("user")RegistrationDto user, BindingResult result) {
		User existingUserEmail = userService.getUserByEmail(user.getEmail());
		if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }
		User existingUserNameLogin = userService.getUserByNameLogin(user.getUsername());
		if(existingUserNameLogin != null && existingUserNameLogin.getNameLogin() != null && !existingUserNameLogin.getNameLogin().isEmpty()) {
            return "redirect:/register?fail";
        }
		if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "form/register";
        }
		userService.saveUser(user);
		
		return "redirect:/form/login";
	}
}

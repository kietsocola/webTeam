package com.myProject.webteam.services;

import com.myProject.webteam.dto.RegistrationDto;
import com.myProject.webteam.models.Project;
import com.myProject.webteam.models.User;

public interface UserService {
	User getUserById(int id);
	User getUserByEmail(String email);
	User getUserByNameLogin(String name);
	void saveUser(RegistrationDto registrationDto, Project project);
	void saveUser(RegistrationDto registrationDto);
}

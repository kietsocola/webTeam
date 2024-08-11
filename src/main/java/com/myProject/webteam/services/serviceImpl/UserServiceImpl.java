package com.myProject.webteam.services.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myProject.webteam.dto.RegistrationDto;
import com.myProject.webteam.models.Project;
import com.myProject.webteam.models.ProjectRole;
import com.myProject.webteam.models.Role;
import com.myProject.webteam.models.User;
import com.myProject.webteam.respositories.ProjectRoleResponsitory;
import com.myProject.webteam.respositories.RoleResponsitory;
import com.myProject.webteam.respositories.UserRespository;
import com.myProject.webteam.services.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRespository userRepo;
	@Autowired
	private RoleResponsitory roleRepo;
	@Autowired
	private ProjectRoleResponsitory proRoleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub 
		return userRepo.findById(id).orElse(null);
	}
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email).orElse(null);
	}
	@Override
	public User getUserByNameLogin(String name) {
		// TODO Auto-generated method stub
		return userRepo.findByNameLogin(name).orElse(null);
	}
	@Override
	public void saveUser(RegistrationDto registrationDto, Project project) {
		User user = new User();
		user.setNameLogin(registrationDto.getUsername());
		user.setEmail(registrationDto.getEmail());
		user.setPassword(registrationDto.getPassword());
		user.setStatus(1);
		user.setDateCreate(LocalDateTime.now());
		userRepo.save(user);
		
		Role role = roleRepo.findByName("User");
		ProjectRole proRole = new ProjectRole();
		proRole.setProject(project);
		proRole.setRole(role);
		proRole.setUser(user);
		proRoleRepo.save(proRole);
		
		
	}
	@Override
	public void saveUser(RegistrationDto registrationDto) {
		User user = new User();
		user.setNameLogin(registrationDto.getUsername());
		user.setEmail(registrationDto.getEmail());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		user.setStatus(1);
		user.setDateCreate(LocalDateTime.now());
		userRepo.save(user);
	}

}

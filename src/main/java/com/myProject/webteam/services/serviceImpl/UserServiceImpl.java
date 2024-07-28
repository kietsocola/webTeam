package com.myProject.webteam.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.webteam.models.User;
import com.myProject.webteam.respositories.UserRespository;
import com.myProject.webteam.services.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRespository userRepo;
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).get();
	}

}

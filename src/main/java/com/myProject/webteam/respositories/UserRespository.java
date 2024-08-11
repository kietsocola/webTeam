package com.myProject.webteam.respositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.webteam.models.User;

public interface UserRespository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
	Optional<User> findByNameLogin(String name);
	User findFirstByNameLogin(String name);
}

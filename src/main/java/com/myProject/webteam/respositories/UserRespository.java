package com.myProject.webteam.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.webteam.models.User;

public interface UserRespository extends JpaRepository<User, Integer>{

}

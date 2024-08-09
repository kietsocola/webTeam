package com.myProject.webteam.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.webteam.models.Role;

public interface RoleResponsitory extends JpaRepository<Role, Integer>{
	Role findByName(String name);
}

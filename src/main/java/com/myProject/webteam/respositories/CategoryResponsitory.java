package com.myProject.webteam.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.webteam.models.Category;

public interface CategoryResponsitory extends JpaRepository<Category, Integer>{

}

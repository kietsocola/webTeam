package com.myProject.webteam.services.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.webteam.models.Category;
import com.myProject.webteam.respositories.CategoryResponsitory;
import com.myProject.webteam.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryResponsitory cateRepo;
	@Override
	public List<Category> getListCategory() {
		// TODO Auto-generated method stub
		return cateRepo.findAll();
	}

}

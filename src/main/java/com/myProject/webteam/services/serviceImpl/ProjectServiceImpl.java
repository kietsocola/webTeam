package com.myProject.webteam.services.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.webteam.models.Project;
import com.myProject.webteam.models.User;
import com.myProject.webteam.respositories.ProjectResponsitory;
import com.myProject.webteam.respositories.UserRespository;
import com.myProject.webteam.services.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	private ProjectResponsitory projectResponsitory;
	@Autowired
	public ProjectServiceImpl(ProjectResponsitory projectResponsitory, UserRespository userRepo) {
		this.projectResponsitory = projectResponsitory;
	}
	@Override
	public List<Project> getListProject() {
		return projectResponsitory.findAll();
	}
	@Override
	public Project getProjectById(int id) {
		return projectResponsitory.getReferenceById(id);
	}
	@Override
	public Project saveProject(String name) {
		Project pro = new Project();
		pro.setName(name);
		return projectResponsitory.save(pro);
	}
	@Override
	public Project saveProject(Project pro) {
		return projectResponsitory.save(pro);
	}
	@Override
	public List<Project> getListProjectByUserId(int id) {
		return projectResponsitory.findByUsers_Id(id);
	}

}

package com.myProject.webteam.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.webteam.models.Point;
import com.myProject.webteam.models.Project;
import com.myProject.webteam.models.User;
import com.myProject.webteam.respositories.PointResponsitory;
import com.myProject.webteam.services.PointService;

@Service
public class PointServiceImpl implements PointService{

	@Autowired
	private PointResponsitory pointRepo;
	@Override
	public Point createPointFor_newUser(Project pro, User u) {
		Point point = new Point();
		point.setUser(u);
		point.setProject(pro);
		point.setPoint(100);
		return pointRepo.save(point);
	}
}

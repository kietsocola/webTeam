package com.myProject.webteam.services;

import com.myProject.webteam.models.Point;
import com.myProject.webteam.models.Project;
import com.myProject.webteam.models.User;

public interface PointService {
	Point createPointFor_newUser(Project pro, User u); 
}

package com.myProject.webteam.services.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.webteam.models.Point;
import com.myProject.webteam.models.Project;
import com.myProject.webteam.models.Task;
import com.myProject.webteam.models.User;
import com.myProject.webteam.respositories.PointResponsitory;
import com.myProject.webteam.respositories.TaskResponsitory;
import com.myProject.webteam.respositories.UserRespository;
import com.myProject.webteam.services.PointService;

@Service
public class PointServiceImpl implements PointService{

	@Autowired
	private PointResponsitory pointRepo;
	@Autowired
    private TaskResponsitory taskRepo;
	@Override
	public Point createPointFor_newUser(Project pro, User u) {
		Point point = new Point();
		point.setUser(u);
		point.setProject(pro);
		point.setPoint(100);
		return pointRepo.save(point);
	}
	@Override
	public void deductPointsForOverdueTasks() {
		List<Task> overdueTasks = taskRepo.findByDateEndBeforeAndStatusNot(LocalDateTime.now(), 2);
		List<Point> points = pointRepo.findAll();
		for(Task task: overdueTasks) {
			for(Point p: points) {
				if(p.getUser().getId() == task.getUserReceive().getId() && p.getProject().getId() == task.getProject().getId()) {
					p.setPoint(p.getPoint()-task.getPoint());
					pointRepo.save(p);
					task.setCompleted(0);
					taskRepo.save(task);
				}
			}
		}
	}
}

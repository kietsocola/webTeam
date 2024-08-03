package com.myProject.webteam.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.webteam.models.Point;

public interface PointResponsitory extends JpaRepository<Point, Integer> {

}

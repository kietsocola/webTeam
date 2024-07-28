package com.myProject.webteam.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.webteam.models.Comment;

public interface CommentResponsitory extends JpaRepository<Comment, Integer> {

}

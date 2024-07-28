package com.myProject.webteam.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.webteam.models.Comment;
import com.myProject.webteam.respositories.CommentResponsitory;
import com.myProject.webteam.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentResponsitory cmtRepo;
	@Override
	public Comment saveComment(Comment cmt) {
		return cmtRepo.save(cmt);
	}

}

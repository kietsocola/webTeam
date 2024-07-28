package com.myProject.webteam.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="commenttask")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String content;
	@Column(name="dateCreate")
	private LocalDateTime dateCreate;

	@ManyToOne
	@JoinColumn(name="idUser", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="idTask", nullable = false)
	private Task task;

	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}

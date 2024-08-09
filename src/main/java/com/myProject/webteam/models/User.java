package com.myProject.webteam.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nameLogin;
	private String password;
	private String email;
	private int status;
	@Column(name="dateCreate")
	private LocalDateTime dateCreate;
	@OneToMany(mappedBy="user", cascade = CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<>();
	@OneToMany(mappedBy="userCreate", cascade = CascadeType.REMOVE)
	private List<Task> tasks = new ArrayList<>();
	@OneToMany(mappedBy="userReceive", cascade = CascadeType.REMOVE)
	private List<Task> tasksReceived = new ArrayList<>();
	@OneToMany(mappedBy="user", cascade = CascadeType.REMOVE)
	private List<Point> point = new ArrayList<>();
	@OneToMany(mappedBy = "user")
    private List<ProjectRole> projectRoles;
	public List<Task> getTasksReceived() {
		return tasksReceived;
	}
	public void setTasksReceived(List<Task> tasksReceived) {
		this.tasksReceived = tasksReceived;
	}
	@ManyToMany(mappedBy = "users")
	private List<Project> projects = new ArrayList<>();
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameLogin() {
		return nameLogin;
	}
	public void setNameLogin(String nameLogin) {
		this.nameLogin = nameLogin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public LocalDateTime getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Point> getPoint() {
		return point;
	}
	public void setPoint(List<Point> point) {
		this.point = point;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<ProjectRole> getProjectRoles() {
		return projectRoles;
	}
	public void setProjectRoles(List<ProjectRole> projectRoles) {
		this.projectRoles = projectRoles;
	}
	
}

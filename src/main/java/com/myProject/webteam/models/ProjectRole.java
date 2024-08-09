package com.myProject.webteam.models;

import jakarta.persistence.*;

@Entity
public class ProjectRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	@ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
	@ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}

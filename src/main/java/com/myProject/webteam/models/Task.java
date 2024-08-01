package com.myProject.webteam.models;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name="dateCreate")
	private LocalDateTime dateCreate;
	@Column(name="dateEnd")
	private LocalDateTime dateEnd;
	private int point;
	@ManyToOne
	@JoinColumn(name="category", nullable = false)
	private Category category;
	private int level;
	private int status;
	@ManyToOne
    @JoinColumn(name="project", nullable = false)
	private Project project;
	private String description;
	private long hoursRemaining;
	@ManyToOne
    @JoinColumn(name="userCreate", nullable = false)
	private User userCreate;
	@ManyToOne
    @JoinColumn(name="userReceive", nullable = false)
	private User userReceive;
	public User getUserReceive() {
		return userReceive;
	}
	public void setUserReceive(User userReceive) {
		this.userReceive = userReceive;
	}

	@OneToMany(mappedBy="task", cascade = CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<>();
	public User getUserCreate() {
		return userCreate;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public void setUserCreate(User userCreate) {
		this.userCreate = userCreate;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}
	public LocalDateTime getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(LocalDateTime dateEnd) {
		this.dateEnd = dateEnd;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Project getProject() {
		return project;
	}
	public void setIdProject(Project idProject) {
		this.project = idProject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getHoursRemaining() {
        return hoursRemaining;
    }

    public void setHoursRemaining(long hoursRemaining) {
        this.hoursRemaining = hoursRemaining;
    }
	
	
}

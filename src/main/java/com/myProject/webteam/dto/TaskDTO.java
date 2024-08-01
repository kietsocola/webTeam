package com.myProject.webteam.dto;

import jakarta.validation.constraints.*;

public class TaskDTO {
	private int id;
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@NotBlank(message = "Name is required")
	private String name;
	@NotNull(message = "Point is required")
    @Min(value = 1, message = "Point must be at least 1")
    private int point;
    private String description;
    private int level;
    private String category;
    @NotNull(message = "End date is required")
    private String dateEnd;
    public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	private int userCreate;
	private int userReceive;
    public int getUserReceive() {
		return userReceive;
	}

	public void setUserReceive(int userReceive) {
		this.userReceive = userReceive;
	}
	private int idProject;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(int userCreate) {
        this.userCreate = userCreate;
    }
}

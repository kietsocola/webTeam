package com.myProject.webteam.mapper;

import com.myProject.webteam.dto.TaskDTO;
import com.myProject.webteam.models.Task;
import com.myProject.webteam.models.Category;
import com.myProject.webteam.models.Project;
import com.myProject.webteam.models.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    public static Task toTask(TaskDTO taskDTO) {
    	if (taskDTO == null) {
            return null;
        }

        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setPoint(taskDTO.getPoint());
        task.setDescription(taskDTO.getDescription());
        task.setLevel(taskDTO.getLevel());
        task.setDateEnd(LocalDateTime.parse(taskDTO.getDateEnd(), DATE_TIME_FORMATTER));

        // Assuming you have a method to get Category and User by ID
        Category category = new Category();
        category.setId(Integer.parseInt(taskDTO.getCategory()));
        task.setCategory(category);

        User user = new User();
        user.setId(1);
        task.setUserCreate(user);
        
        Project project = new Project();
        project.setId(taskDTO.getIdProject());
        task.setProject(project);
        
        return task;
    }

    public static TaskDTO toTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName(task.getName());
        taskDTO.setPoint(task.getPoint());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setLevel(task.getLevel());
        taskDTO.setDateEnd(task.getDateEnd().format(DATE_TIME_FORMATTER));
        taskDTO.setCategory(String.valueOf(task.getCategory().getId()));
        taskDTO.setUserCreate(String.valueOf(task.getUserCreate().getId()));
        return taskDTO;
    }
}

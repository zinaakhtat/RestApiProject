package com.AppsAgile.RestApi.Services;

import com.AppsAgile.RestApi.Dto.TaskDTO;
import com.AppsAgile.RestApi.Entities.Task;
import java.util.List;

public interface TaskService {

    TaskDTO createTask(TaskDTO taskDTO);

    TaskDTO updateTask(Long id, TaskDTO taskDTO);

    TaskDTO getTaskById(Long id);

    List<TaskDTO> getAllTasks();

    void deleteTask(Long id);
}

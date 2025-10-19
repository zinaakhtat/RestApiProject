package com.AppsAgile.RestApi.DtoMappers;

import com.AppsAgile.RestApi.Dto.TaskDTO;
import com.AppsAgile.RestApi.Entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper{

    // Convertit une entité Task en DTO
    public TaskDTO toDTO(Task task) {
        if (task == null) return null;

        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitre(task.getTitre());
        dto.setDescription(task.getDescription());
        dto.setStatut(task.getStatut());

        if (task.getUserStory() != null) {
            dto.setUserStoryId(task.getUserStory().getId());
        }

        if (task.getSprintBacklog() != null) {

            dto.setSprintBacklogId(task.getSprintBacklog().getId());
        }

        return dto;
    }

    // Convertit un DTO Task en entité Task (sans les associations)
    public Task toEntity(TaskDTO dto) {
        if (dto == null) return null;

        Task task = new Task();
        task.setId(dto.getId());
        task.setTitre(dto.getTitre());
        task.setDescription(dto.getDescription());
        task.setStatut(dto.getStatut());

        // Associations ignorées : elles doivent être gérées manuellement
        task.setUserStory(null);
        task.setSprintBacklog(null);

        return task;
    }

    // Met à jour une entité existante à partir d’un DTO (sans écraser les relations)
    public void updateEntityFromDTO(TaskDTO dto, Task entity) {
        if (dto == null || entity == null) return;

        if (dto.getTitre() != null) entity.setTitre(dto.getTitre());
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
        if (dto.getStatut() != null) entity.setStatut(dto.getStatut());

        // On ne modifie pas userStory ni sprintBacklog ici
    }
}

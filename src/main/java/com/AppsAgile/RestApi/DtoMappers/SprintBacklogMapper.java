package com.AppsAgile.RestApi.DtoMappers;

import com.AppsAgile.RestApi.Dto.SprintBacklogDTO;
import com.AppsAgile.RestApi.Entities.SprintBacklog;
import com.AppsAgile.RestApi.Entities.Task;
import com.AppsAgile.RestApi.Entities.UserStory;

import java.util.List;
import java.util.stream.Collectors;

public class SprintBacklogMapper {

    // DTO -> Entity
    public static SprintBacklog toEntity(SprintBacklogDTO dto, List<UserStory> userStories, List<Task> tasks) {
        SprintBacklog entity = new SprintBacklog();
        entity.setId(dto.getId());
        updateEntityFromDTO(entity, dto, userStories, tasks);
        return entity;
    }

    // Entity -> DTO
    public static SprintBacklogDTO toDTO(SprintBacklog entity) {
        List<Long> userStoryIds = entity.getUserStories() != null
                ? entity.getUserStories().stream().map(UserStory::getId).collect(Collectors.toList())
                : null;

        List<Long> taskIds = entity.getTasks() != null
                ? entity.getTasks().stream().map(Task::getId).collect(Collectors.toList())
                : null;

        return SprintBacklogDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .datedebut(entity.getDatedebut())
                .datefin(entity.getDatefin())
                .userStoryIds(userStoryIds)
                .taskIds(taskIds)
                .build();
    }

    // Mise à jour d'une entité existante avec les valeurs du DTO
    public static void updateEntityFromDTO(SprintBacklog entity, SprintBacklogDTO dto, List<UserStory> userStories, List<Task> tasks) {
        entity.setNom(dto.getNom());
        entity.setDatedebut(dto.getDatedebut());
        entity.setDatefin(dto.getDatefin());
        entity.setUserStories(userStories);
        entity.setTasks(tasks);
    }
}

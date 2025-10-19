package com.AppsAgile.RestApi.DtoMappers;

import com.AppsAgile.RestApi.Dto.ProductBacklogDTO;
import com.AppsAgile.RestApi.Entities.Epic;
import com.AppsAgile.RestApi.Entities.ProductBacklog;
import com.AppsAgile.RestApi.Entities.UserStory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProductBacklogMapper {

    // DTO -> Entity
    public static ProductBacklog toEntity(ProductBacklogDTO dto, List<UserStory> userStories, List<Epic> epics) {
        return ProductBacklog.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .userStories(userStories)
                .epics(epics)
                .build();
    }

    // Entity -> DTO
    public static ProductBacklogDTO toDTO(ProductBacklog entity) {
        List<Long> userStoryIds = entity.getUserStories() != null
                ? entity.getUserStories().stream().map(UserStory::getId).collect(Collectors.toList())
                : null;

        List<Long> epicIds = entity.getEpics() != null
                ? entity.getEpics().stream().map(Epic::getId).collect(Collectors.toList())
                : null;

        return ProductBacklogDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .userStoryIds(userStoryIds)
                .epicIds(epicIds)
                .build();
    }

    // Mise à jour d'une entité existante avec les valeurs du DTO
    public static void updateEntityFromDTO(ProductBacklog entity, ProductBacklogDTO dto, List<UserStory> userStories, List<Epic> epics) {
        entity.setNom(dto.getNom());
        entity.setUserStories(userStories);
        entity.setEpics(epics);
    }
}

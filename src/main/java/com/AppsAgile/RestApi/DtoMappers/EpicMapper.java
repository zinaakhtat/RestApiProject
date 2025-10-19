package com.AppsAgile.RestApi.DtoMappers;

import com.AppsAgile.RestApi.Dto.EpicDTO;
import com.AppsAgile.RestApi.Entities.Epic;
import com.AppsAgile.RestApi.Entities.UserStory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EpicMapper {

    // Convertir Entity → DTO
    public EpicDTO toDTO(Epic epic) {
        if (epic == null) return null;

        EpicDTO dto = new EpicDTO();
        dto.setId(epic.getId());
        dto.setTitre(epic.getTitre());
        dto.setDescription(epic.getDescription());

        // Map les IDs des user stories
        if (epic.getUserStories() != null) {
            List<Long> userStoryIds = epic.getUserStories()
                    .stream()
                    .map(UserStory::getId)
                    .collect(Collectors.toList());
            dto.setUserStoryIds(userStoryIds);
        }

        // Set juste l'ID du product backlog (pas l'objet complet)
        if (epic.getProductBacklog() != null) {
            dto.setProductBacklogId(epic.getProductBacklog().getId());
        }

        return dto;
    }

    // Convertir DTO → Entity (relations ignorées ici)
    public Epic toEntity(EpicDTO dto) {
        if (dto == null) return null;

        Epic epic = new Epic();
        epic.setId(dto.getId());
        epic.setTitre(dto.getTitre());
        epic.setDescription(dto.getDescription());

        // userStories et productBacklog seront injectés ailleurs (service ou controller)
        return epic;
    }

    // Mise à jour d'une entité existante (sans toucher aux relations)
    public void updateEntityFromDTO(EpicDTO dto, Epic entity) {
        if (dto == null || entity == null) return;

        entity.setTitre(dto.getTitre());
        entity.setDescription(dto.getDescription());
        // Pas de mise à jour ici pour userStories ni productBacklog
    }
}

package com.AppsAgile.RestApi.DtoMappers;

import com.AppsAgile.RestApi.Dto.UserStoryDTO;
import com.AppsAgile.RestApi.Dto.DescriptionDTO;
import com.AppsAgile.RestApi.Entities.UserStory;
import com.AppsAgile.RestApi.Entities.Description;
import com.AppsAgile.RestApi.Entities.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserStoryMapper {


    public UserStoryDTO toDTO(UserStory entity) {
        if (entity == null) return null;

        UserStoryDTO dto = new UserStoryDTO();
        dto.setId(entity.getId());
        dto.setTitre(entity.getTitre());
        dto.setPriorite(entity.getPriorite());
        dto.setStatut(entity.getStatut());

        // Description
        dto.setDescription(toDescriptionDTO(entity.getDescription()));

        // Tasks → Liste d'IDs
        if (entity.getTasks() != null) {
            List<Long> taskIds = entity.getTasks().stream()
                    .map(Task::getId)
                    .collect(Collectors.toList());
            dto.setTaskIds(taskIds);

        }

        // SprintBacklog ID
        if (entity.getSprintBacklog() != null) {
            dto.setSprintBacklogId(entity.getSprintBacklog().getId());

        }

        // Epic ID
        if (entity.getEpic() != null) {
            dto.setEpicId(entity.getEpic().getId());

        }


        if (entity.getProductBacklog() != null) {
            dto.setProductBacklogId(entity.getProductBacklog().getId());
        }

        return dto;
    }

    public UserStory toEntity(UserStoryDTO dto) {
        if (dto == null) return null;

        UserStory entity = new UserStory();
        entity.setId(dto.getId());
        entity.setTitre(dto.getTitre());
        entity.setPriorite(dto.getPriorite());
        entity.setStatut(dto.getStatut());


        entity.setDescription(null);
        entity.setTasks(null);
        entity.setSprintBacklog(null);
        entity.setEpic(null);
        entity.setProductBacklog(null);

        return entity;
    }


    public void updateEntityFromDTO(UserStoryDTO dto, UserStory entity) {
        if (dto == null || entity == null) return;

        if (dto.getTitre() != null) entity.setTitre(dto.getTitre());
        if (dto.getPriorite() != null) entity.setPriorite(dto.getPriorite());
        if (dto.getStatut() != null) entity.setStatut(dto.getStatut());


    }


    private DescriptionDTO toDescriptionDTO(Description description) {
        if (description == null) return null;

        DescriptionDTO dto = new DescriptionDTO();
        dto.setId(description.getId());
        dto.setRole(description.getRole());
        dto.setBesoin(description.getBesoin());
        dto.setRaison(description.getRaison());
        return dto;
    }
}

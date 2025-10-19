package com.AppsAgile.RestApi.Dto;

import com.AppsAgile.RestApi.Enumurations.Priorite;
import com.AppsAgile.RestApi.Enumurations.Statut;
import lombok.*;
import lombok.Setter;
import java.util.List;
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStoryDTO {
    private Long id;
    private String titre;
    private Priorite priorite;
    private Statut statut;
    private DescriptionDTO description;
    private List<Long> taskIds;
    private Long sprintBacklogId;
    private Long epicId;
    private Long productBacklogId;
}

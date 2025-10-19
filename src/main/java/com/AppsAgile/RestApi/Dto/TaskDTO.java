package com.AppsAgile.RestApi.Dto;

import com.AppsAgile.RestApi.Enumurations.Statut;
import lombok.*;
import lombok.Setter;
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String titre;
    private String description;
    private Statut statut;
    private Long userStoryId;
    private Long sprintBacklogId;
}

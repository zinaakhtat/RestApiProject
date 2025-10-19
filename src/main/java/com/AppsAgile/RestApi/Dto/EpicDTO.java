package com.AppsAgile.RestApi.Dto;

import lombok.*;
import lombok.Setter;
import java.util.List;
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpicDTO {
    private Long id;
    private String titre;
    private String description;
    private List<Long> userStoryIds;     // Référence aux UserStories
    private Long productBacklogId;       // Référence au ProductBacklog
}

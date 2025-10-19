package com.AppsAgile.RestApi.Dto;

import lombok.*;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SprintBacklogDTO {
    private Long id;
    private String nom;
    private LocalDate datedebut;
    private LocalDate datefin;

    private List<Long> userStoryIds;
    private List<Long> taskIds;


}

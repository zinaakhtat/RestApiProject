package com.AppsAgile.RestApi.Dto;
import lombok.Setter;
import lombok.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


import java.util.List;
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductBacklogDTO {
    private Long id;
    private String nom;

    // IDs des User Stories associées
    private List<Long> userStoryIds;

    // IDs des Epics associées
    private List<Long> epicIds;


}

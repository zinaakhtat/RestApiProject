package com.AppsAgile.RestApi.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SprintBacklog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private LocalDate datedebut;
    private LocalDate datefin;

    @OneToMany(mappedBy = "sprintBacklog")
    private List<UserStory> userStories=new ArrayList<>();

    @OneToMany(mappedBy = "sprintBacklog", cascade = CascadeType.ALL)
    private List<Task> tasks=new ArrayList<>();



}

package com.AppsAgile.RestApi.Entities;


import com.AppsAgile.RestApi.Enumurations.Statut;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    @ManyToOne
    @JoinColumn
    private UserStory userStory;

    @ManyToOne
    @JoinColumn
    private SprintBacklog sprintBacklog;
}

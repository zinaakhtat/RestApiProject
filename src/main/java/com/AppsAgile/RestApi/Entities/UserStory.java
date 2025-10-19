package com.AppsAgile.RestApi.Entities;


import com.AppsAgile.RestApi.Enumurations.Priorite;
import com.AppsAgile.RestApi.Enumurations.Statut;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @Enumerated(EnumType.STRING)
    private Priorite priorite;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    @OneToOne( cascade = CascadeType.ALL)
    private Description description;

    @OneToMany(mappedBy = "userStory", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn
    private SprintBacklog sprintBacklog;

    @ManyToOne
    @JoinColumn
    private Epic epic;

    @ManyToOne
    @JoinColumn
    private ProductBacklog productBacklog;


}

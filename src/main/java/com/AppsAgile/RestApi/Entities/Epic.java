package com.AppsAgile.RestApi.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Epic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;

    @OneToMany(mappedBy = "epic")
    private List<UserStory> userStories;

    @ManyToOne
    @JoinColumn
    private ProductBacklog productBacklog;
}

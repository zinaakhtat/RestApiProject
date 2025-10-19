package com.AppsAgile.RestApi.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductBacklog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "productBacklog")
    private List<UserStory> userStories;

    @OneToMany(mappedBy = "productBacklog")
    private List<Epic> epics;


}

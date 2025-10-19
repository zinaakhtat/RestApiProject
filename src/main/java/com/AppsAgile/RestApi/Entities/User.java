package com.AppsAgile.RestApi.Entities;

import com.AppsAgile.RestApi.Enumurations.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name="passwd",nullable = false)
    private String passwd;

    @Enumerated(EnumType.STRING)
    @Column(name="roleuser",nullable = false)
    private Role role;

    @Version
    @Column(name = "version")
    private Long version;


}
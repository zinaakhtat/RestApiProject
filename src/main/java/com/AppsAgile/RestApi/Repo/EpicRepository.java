package com.AppsAgile.RestApi.Repo;

import com.AppsAgile.RestApi.Entities.Epic;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface EpicRepository extends JpaRepository<Epic, Long> {
}
package com.AppsAgile.RestApi.Repo;

import com.AppsAgile.RestApi.Entities.SprintBacklog;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface SprintBacklogRepository extends JpaRepository<SprintBacklog, Long> {
}

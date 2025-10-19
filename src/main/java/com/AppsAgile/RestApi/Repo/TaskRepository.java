package com.AppsAgile.RestApi.Repo;

import com.AppsAgile.RestApi.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface TaskRepository extends JpaRepository<Task, Long> {
}
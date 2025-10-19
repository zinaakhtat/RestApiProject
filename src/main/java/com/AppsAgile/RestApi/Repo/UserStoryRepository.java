package com.AppsAgile.RestApi.Repo;

import com.AppsAgile.RestApi.Entities.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface UserStoryRepository extends JpaRepository<UserStory, Long> {
}

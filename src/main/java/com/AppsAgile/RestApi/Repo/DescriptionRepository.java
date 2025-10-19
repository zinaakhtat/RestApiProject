package com.AppsAgile.RestApi.Repo;

import com.AppsAgile.RestApi.Entities.Description;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {
}

package com.AppsAgile.RestApi.Repo;


import com.AppsAgile.RestApi.Entities.ProductBacklog;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface ProductBacklogRepository extends JpaRepository<ProductBacklog, Long> {
}
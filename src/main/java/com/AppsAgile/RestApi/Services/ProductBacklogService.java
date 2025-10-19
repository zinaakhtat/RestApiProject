package com.AppsAgile.RestApi.Services;

import com.AppsAgile.RestApi.Dto.ProductBacklogDTO;

import java.util.List;

public interface ProductBacklogService {
    ProductBacklogDTO create(ProductBacklogDTO dto);
    ProductBacklogDTO getById(Long id);
    List<ProductBacklogDTO> getAll();
    ProductBacklogDTO update(Long id, ProductBacklogDTO dto);
    void delete(Long id);
}

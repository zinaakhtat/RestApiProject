package com.AppsAgile.RestApi.Services;

import com.AppsAgile.RestApi.Dto.SprintBacklogDTO;

import java.util.List;

public interface SprintBacklogService {
    SprintBacklogDTO create(SprintBacklogDTO dto);
    SprintBacklogDTO getById(Long id);
    List<SprintBacklogDTO> getAll();
    SprintBacklogDTO update(Long id, SprintBacklogDTO dto);
    void delete(Long id);
}

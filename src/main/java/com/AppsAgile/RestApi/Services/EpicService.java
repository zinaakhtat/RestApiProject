package com.AppsAgile.RestApi.Services;

import com.AppsAgile.RestApi.Dto.EpicDTO;

import java.util.List;

public interface EpicService {
    EpicDTO create(EpicDTO dto);
    EpicDTO getById(Long id);
    List<EpicDTO> getAll();
    EpicDTO update(Long id, EpicDTO dto);
    void delete(Long id);
}

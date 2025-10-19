
package com.AppsAgile.RestApi.Services;

import com.AppsAgile.RestApi.Dto.DescriptionDTO;

import java.util.List;

public interface DescriptionService {
    DescriptionDTO create(DescriptionDTO dto);
    DescriptionDTO getById(Long id);
    List<DescriptionDTO> getAll();
    DescriptionDTO update(Long id, DescriptionDTO dto);
    void delete(Long id);
}

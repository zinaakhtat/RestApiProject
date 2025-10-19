package com.AppsAgile.RestApi.DtoMappers;

import com.AppsAgile.RestApi.Entities.Description;
import com.AppsAgile.RestApi.Dto.DescriptionDTO;

public class DescriptionMapper {

    public static DescriptionDTO toDTO(Description entity) {
        return (entity == null) ? null : new DescriptionDTO(
                entity.getId(),
                entity.getRole(),
                entity.getBesoin(),
                entity.getRaison()
        );
    }

    public static Description toEntity(DescriptionDTO dto) {
        return (dto == null) ? null : new Description(
                dto.getId(),
                dto.getRole(),
                dto.getBesoin(),
                dto.getRaison()
        );
    }
}

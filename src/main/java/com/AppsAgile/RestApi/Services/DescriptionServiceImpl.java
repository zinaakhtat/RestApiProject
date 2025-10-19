package com.AppsAgile.RestApi.Services;

import com.AppsAgile.RestApi.Dto.DescriptionDTO;
import com.AppsAgile.RestApi.DtoMappers.DescriptionMapper;
import com.AppsAgile.RestApi.Entities.Description;
import com.AppsAgile.RestApi.Repo.DescriptionRepository;
import com.AppsAgile.RestApi.Services.DescriptionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DescriptionServiceImpl implements DescriptionService {

    private final DescriptionRepository descriptionRepository;

    public DescriptionServiceImpl(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    @Override
    public DescriptionDTO create(DescriptionDTO dto) {
        Description entity = DescriptionMapper.toEntity(dto);
        Description saved = descriptionRepository.save(entity);
        return DescriptionMapper.toDTO(saved);
    }

    @Override
    public DescriptionDTO getById(Long id) {
        Description entity = descriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Description not found with ID: " + id));
        return DescriptionMapper.toDTO(entity);
    }

    @Override
    public List<DescriptionDTO> getAll() {
        return descriptionRepository.findAll().stream()
                .map(DescriptionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DescriptionDTO update(Long id, DescriptionDTO dto) {
        Description existing = descriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Description not found with ID: " + id));

        existing.setRole(dto.getRole());
        existing.setBesoin(dto.getBesoin());
        existing.setRaison(dto.getRaison());

        Description updated = descriptionRepository.save(existing);
        return DescriptionMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (!descriptionRepository.existsById(id)) {
            throw new EntityNotFoundException("Description not found with ID: " + id);
        }
        descriptionRepository.deleteById(id);
    }
}

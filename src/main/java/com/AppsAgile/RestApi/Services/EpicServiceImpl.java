package com.AppsAgile.RestApi.Services;

import com.AppsAgile.RestApi.Dto.EpicDTO;
import com.AppsAgile.RestApi.DtoMappers.EpicMapper;
import com.AppsAgile.RestApi.Entities.Epic;
import com.AppsAgile.RestApi.Entities.ProductBacklog;
import com.AppsAgile.RestApi.Entities.UserStory;
import com.AppsAgile.RestApi.Repo.EpicRepository;
import com.AppsAgile.RestApi.Repo.ProductBacklogRepository;
import com.AppsAgile.RestApi.Repo.UserStoryRepository;
import com.AppsAgile.RestApi.Services.EpicService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EpicServiceImpl implements EpicService {

    private final EpicRepository epicRepository;
    private final ProductBacklogRepository productBacklogRepository;
    private final UserStoryRepository userStoryRepository;
    private final EpicMapper epicMapper;

    public EpicServiceImpl(EpicRepository epicRepository,
                           ProductBacklogRepository productBacklogRepository,
                           UserStoryRepository userStoryRepository,
                           EpicMapper epicMapper) {
        this.epicRepository = epicRepository;
        this.productBacklogRepository = productBacklogRepository;
        this.userStoryRepository = userStoryRepository;
        this.epicMapper = epicMapper;
    }

    @Override
    public EpicDTO create(EpicDTO dto) {
        Epic epic = epicMapper.toEntity(dto);

        // Injecter le ProductBacklog
        if (dto.getProductBacklogId() != null) {
            ProductBacklog pb = productBacklogRepository.findById(dto.getProductBacklogId())
                    .orElseThrow(() -> new EntityNotFoundException("ProductBacklog not found with ID: " + dto.getProductBacklogId()));
            epic.setProductBacklog(pb);
        }

        // Injecter les UserStories
        if (dto.getUserStoryIds() != null) {
            List<UserStory> userStories = userStoryRepository.findAllById(dto.getUserStoryIds());
            epic.setUserStories(userStories);
        }

        Epic saved = epicRepository.save(epic);
        return epicMapper.toDTO(saved);
    }

    @Override
    public EpicDTO getById(Long id) {
        Epic epic = epicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Epic not found with ID: " + id));
        return epicMapper.toDTO(epic);
    }

    @Override
    public List<EpicDTO> getAll() {
        return epicRepository.findAll().stream()
                .map(epicMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EpicDTO update(Long id, EpicDTO dto) {
        Epic existing = epicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Epic not found with ID: " + id));

        epicMapper.updateEntityFromDTO(dto, existing);

        // Mettre à jour les UserStories si fournies
        if (dto.getUserStoryIds() != null) {
            List<UserStory> userStories = userStoryRepository.findAllById(dto.getUserStoryIds());
            existing.setUserStories(userStories);
        }

        // Mettre à jour le ProductBacklog si fourni
        if (dto.getProductBacklogId() != null) {
            ProductBacklog pb = productBacklogRepository.findById(dto.getProductBacklogId())
                    .orElseThrow(() -> new EntityNotFoundException("ProductBacklog not found with ID: " + dto.getProductBacklogId()));
            existing.setProductBacklog(pb);
        }

        Epic updated = epicRepository.save(existing);
        return epicMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (!epicRepository.existsById(id)) {
            throw new EntityNotFoundException("Epic not found with ID: " + id);
        }
        epicRepository.deleteById(id);
    }
}

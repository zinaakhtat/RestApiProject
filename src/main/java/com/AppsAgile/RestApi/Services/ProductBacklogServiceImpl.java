package com.AppsAgile.RestApi.Services;

import com.AppsAgile.RestApi.Dto.ProductBacklogDTO;
import com.AppsAgile.RestApi.DtoMappers.ProductBacklogMapper;
import com.AppsAgile.RestApi.Entities.Epic;
import com.AppsAgile.RestApi.Entities.ProductBacklog;
import com.AppsAgile.RestApi.Entities.UserStory;
import com.AppsAgile.RestApi.Repo.EpicRepository;
import com.AppsAgile.RestApi.Repo.ProductBacklogRepository;
import com.AppsAgile.RestApi.Repo.UserStoryRepository;
import com.AppsAgile.RestApi.Services.ProductBacklogService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBacklogServiceImpl implements ProductBacklogService {

    private final ProductBacklogRepository productBacklogRepository;
    private final UserStoryRepository userStoryRepository;
    private final EpicRepository epicRepository;

    public ProductBacklogServiceImpl(ProductBacklogRepository productBacklogRepository,
                                     UserStoryRepository userStoryRepository,
                                     EpicRepository epicRepository) {
        this.productBacklogRepository = productBacklogRepository;
        this.userStoryRepository = userStoryRepository;
        this.epicRepository = epicRepository;
    }

    @Override
    public ProductBacklogDTO create(ProductBacklogDTO dto) {
        List<UserStory> userStories = dto.getUserStoryIds() != null
                ? userStoryRepository.findAllById(dto.getUserStoryIds())
                : null;

        List<Epic> epics = dto.getEpicIds() != null
                ? epicRepository.findAllById(dto.getEpicIds())
                : null;

        ProductBacklog entity = ProductBacklogMapper.toEntity(dto, userStories, epics);
        ProductBacklog saved = productBacklogRepository.save(entity);
        return ProductBacklogMapper.toDTO(saved);
    }

    @Override
    public ProductBacklogDTO getById(Long id) {
        ProductBacklog entity = productBacklogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProductBacklog not found with ID: " + id));
        return ProductBacklogMapper.toDTO(entity);
    }

    @Override
    public List<ProductBacklogDTO> getAll() {
        return productBacklogRepository.findAll().stream()
                .map(ProductBacklogMapper::toDTO)
                .toList();
    }

    @Override
    public ProductBacklogDTO update(Long id, ProductBacklogDTO dto) {
        ProductBacklog existing = productBacklogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProductBacklog not found with ID: " + id));

        List<UserStory> userStories = dto.getUserStoryIds() != null
                ? userStoryRepository.findAllById(dto.getUserStoryIds())
                : null;

        List<Epic> epics = dto.getEpicIds() != null
                ? epicRepository.findAllById(dto.getEpicIds())
                : null;

        ProductBacklogMapper.updateEntityFromDTO(existing, dto, userStories, epics);
        ProductBacklog updated = productBacklogRepository.save(existing);
        return ProductBacklogMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (!productBacklogRepository.existsById(id)) {
            throw new EntityNotFoundException("ProductBacklog not found with ID: " + id);
        }
        productBacklogRepository.deleteById(id);
    }
}

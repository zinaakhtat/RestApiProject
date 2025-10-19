package com.AppsAgile.RestApi.Services;

import com.AppsAgile.RestApi.Dto.SprintBacklogDTO;
import com.AppsAgile.RestApi.DtoMappers.SprintBacklogMapper;
import com.AppsAgile.RestApi.Entities.SprintBacklog;
import com.AppsAgile.RestApi.Entities.Task;
import com.AppsAgile.RestApi.Entities.UserStory;
import com.AppsAgile.RestApi.Repo.SprintBacklogRepository;
import com.AppsAgile.RestApi.Repo.TaskRepository;
import com.AppsAgile.RestApi.Repo.UserStoryRepository;
import com.AppsAgile.RestApi.Services.SprintBacklogService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintBacklogServiceImpl implements SprintBacklogService {

    private final SprintBacklogRepository sprintBacklogRepository;
    private final UserStoryRepository userStoryRepository;
    private final TaskRepository taskRepository;

    public SprintBacklogServiceImpl(SprintBacklogRepository sprintBacklogRepository,
                                    UserStoryRepository userStoryRepository,
                                    TaskRepository taskRepository) {
        this.sprintBacklogRepository = sprintBacklogRepository;
        this.userStoryRepository = userStoryRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public SprintBacklogDTO create(SprintBacklogDTO dto) {
        List<UserStory> userStories = dto.getUserStoryIds() != null
                ? userStoryRepository.findAllById(dto.getUserStoryIds())
                : null;

        List<Task> tasks = dto.getTaskIds() != null
                ? taskRepository.findAllById(dto.getTaskIds())
                : null;

        SprintBacklog entity = SprintBacklogMapper.toEntity(dto, userStories, tasks);
        SprintBacklog saved = sprintBacklogRepository.save(entity);
        return SprintBacklogMapper.toDTO(saved);
    }

    @Override
    public SprintBacklogDTO getById(Long id) {
        SprintBacklog entity = sprintBacklogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SprintBacklog not found with ID: " + id));
        return SprintBacklogMapper.toDTO(entity);
    }

    @Override
    public List<SprintBacklogDTO> getAll() {
        return sprintBacklogRepository.findAll().stream()
                .map(SprintBacklogMapper::toDTO)
                .toList();
    }

    @Override
    public SprintBacklogDTO update(Long id, SprintBacklogDTO dto) {
        SprintBacklog existing = sprintBacklogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SprintBacklog not found with ID: " + id));

        List<UserStory> userStories = dto.getUserStoryIds() != null
                ? userStoryRepository.findAllById(dto.getUserStoryIds())
                : null;

        List<Task> tasks = dto.getTaskIds() != null
                ? taskRepository.findAllById(dto.getTaskIds())
                : null;

        SprintBacklogMapper.updateEntityFromDTO(existing, dto, userStories, tasks);
        SprintBacklog updated = sprintBacklogRepository.save(existing);
        return SprintBacklogMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (!sprintBacklogRepository.existsById(id)) {
            throw new EntityNotFoundException("SprintBacklog not found with ID: " + id);
        }
        sprintBacklogRepository.deleteById(id);
    }
}

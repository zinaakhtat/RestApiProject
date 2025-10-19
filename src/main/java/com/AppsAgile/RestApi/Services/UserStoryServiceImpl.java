package com.AppsAgile.RestApi.Services;

import com.AppsAgile.RestApi.Dto.UserStoryDTO;
import com.AppsAgile.RestApi.DtoMappers.UserStoryMapper;
import com.AppsAgile.RestApi.Entities.UserStory;
import com.AppsAgile.RestApi.Repo.UserStoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserStoryServiceImpl implements UserStoryService {

    private final UserStoryRepository userStoryRepository;
    private final UserStoryMapper userStoryMapper;

    public UserStoryServiceImpl(UserStoryRepository userStoryRepository,
                                UserStoryMapper userStoryMapper) {
        this.userStoryRepository = userStoryRepository;
        this.userStoryMapper = userStoryMapper;
    }

    @Override
    public UserStoryDTO saveUserStory(UserStoryDTO dto) {
        UserStory entity = userStoryMapper.toEntity(dto);
        UserStory saved = userStoryRepository.save(entity);
        return userStoryMapper.toDTO(saved);
    }

    @Override
    public UserStoryDTO updateUserStory(Long id, UserStoryDTO dto) {
        UserStory existing = userStoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserStory not found with ID: " + id));

        userStoryMapper.updateEntityFromDTO(dto, existing);
        UserStory updated = userStoryRepository.save(existing);
        return userStoryMapper.toDTO(updated);
    }

    @Override
    public void deleteUserStory(Long id) {
        if (!userStoryRepository.existsById(id)) {
            throw new EntityNotFoundException("UserStory not found with ID: " + id);
        }
        userStoryRepository.deleteById(id);
    }

    @Override
    public UserStoryDTO getUserStoryById(Long id) {
        UserStory userStory = userStoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserStory not found with ID: " + id));
        return userStoryMapper.toDTO(userStory);
    }

    @Override
    public List<UserStoryDTO> getAllUserStories() {
        return userStoryRepository.findAll().stream()
                .map(userStoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}

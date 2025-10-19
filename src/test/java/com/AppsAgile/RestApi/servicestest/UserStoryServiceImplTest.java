package com.AppsAgile.RestApi.servicestest;

import com.AppsAgile.RestApi.Dto.UserStoryDTO;
import com.AppsAgile.RestApi.DtoMappers.UserStoryMapper;
import com.AppsAgile.RestApi.Entities.UserStory;
import com.AppsAgile.RestApi.Repo.UserStoryRepository;
import com.AppsAgile.RestApi.Services.UserStoryServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserStoryServiceImplTest {

    @Mock
    private UserStoryRepository userStoryRepository;

    @Mock
    private UserStoryMapper userStoryMapper;

    @InjectMocks
    private UserStoryServiceImpl userStoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUserStory() {
        UserStoryDTO dto = new UserStoryDTO();
        UserStory entity = new UserStory();
        UserStory saved = new UserStory();
        UserStoryDTO savedDto = new UserStoryDTO();

        when(userStoryMapper.toEntity(dto)).thenReturn(entity);
        when(userStoryRepository.save(entity)).thenReturn(saved);
        when(userStoryMapper.toDTO(saved)).thenReturn(savedDto);

        UserStoryDTO result = userStoryService.saveUserStory(dto);

        assertEquals(savedDto, result);
        verify(userStoryRepository).save(entity);
    }

    @Test
    void testUpdateUserStory_Success() {
        Long id = 1L;
        UserStoryDTO dto = new UserStoryDTO();
        UserStory existing = new UserStory();
        UserStory updated = new UserStory();
        UserStoryDTO updatedDto = new UserStoryDTO();

        when(userStoryRepository.findById(id)).thenReturn(Optional.of(existing));
        doNothing().when(userStoryMapper).updateEntityFromDTO(dto, existing);
        when(userStoryRepository.save(existing)).thenReturn(updated);
        when(userStoryMapper.toDTO(updated)).thenReturn(updatedDto);

        UserStoryDTO result = userStoryService.updateUserStory(id, dto);

        assertEquals(updatedDto, result);
        verify(userStoryRepository).save(existing);
    }

    @Test
    void testUpdateUserStory_NotFound() {
        Long id = 1L;
        UserStoryDTO dto = new UserStoryDTO();

        when(userStoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userStoryService.updateUserStory(id, dto));
    }

    @Test
    void testDeleteUserStory_Success() {
        Long id = 1L;
        when(userStoryRepository.existsById(id)).thenReturn(true);

        userStoryService.deleteUserStory(id);

        verify(userStoryRepository).deleteById(id);
    }

    @Test
    void testDeleteUserStory_NotFound() {
        Long id = 1L;
        when(userStoryRepository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> userStoryService.deleteUserStory(id));
    }

    @Test
    void testGetUserStoryById_Success() {
        Long id = 1L;
        UserStory entity = new UserStory();
        UserStoryDTO dto = new UserStoryDTO();

        when(userStoryRepository.findById(id)).thenReturn(Optional.of(entity));
        when(userStoryMapper.toDTO(entity)).thenReturn(dto);

        UserStoryDTO result = userStoryService.getUserStoryById(id);

        assertEquals(dto, result);
    }

    @Test
    void testGetUserStoryById_NotFound() {
        Long id = 1L;
        when(userStoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userStoryService.getUserStoryById(id));
    }

    @Test
    void testGetAllUserStories() {
        List<UserStory> entities = Arrays.asList(new UserStory(), new UserStory());
        List<UserStoryDTO> dtos = Arrays.asList(new UserStoryDTO(), new UserStoryDTO());

        when(userStoryRepository.findAll()).thenReturn(entities);
        when(userStoryMapper.toDTO(entities.get(0))).thenReturn(dtos.get(0));
        when(userStoryMapper.toDTO(entities.get(1))).thenReturn(dtos.get(1));

        List<UserStoryDTO> result = userStoryService.getAllUserStories();

        assertEquals(2, result.size());
        verify(userStoryRepository).findAll();
    }
}

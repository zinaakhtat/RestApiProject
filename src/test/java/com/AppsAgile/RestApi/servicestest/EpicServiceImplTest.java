package com.AppsAgile.RestApi.servicestest;

import com.AppsAgile.RestApi.Dto.EpicDTO;
import com.AppsAgile.RestApi.DtoMappers.EpicMapper;
import com.AppsAgile.RestApi.Entities.Epic;
import com.AppsAgile.RestApi.Entities.ProductBacklog;
import com.AppsAgile.RestApi.Entities.UserStory;
import com.AppsAgile.RestApi.Repo.EpicRepository;
import com.AppsAgile.RestApi.Repo.ProductBacklogRepository;
import com.AppsAgile.RestApi.Repo.UserStoryRepository;
import com.AppsAgile.RestApi.Services.EpicServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EpicServiceImplTest {

    private EpicRepository epicRepository;
    private ProductBacklogRepository productBacklogRepository;
    private UserStoryRepository userStoryRepository;
    private EpicMapper epicMapper;
    private EpicServiceImpl epicService;

    @BeforeEach
    void setUp() {
        epicRepository = mock(EpicRepository.class);
        productBacklogRepository = mock(ProductBacklogRepository.class);
        userStoryRepository = mock(UserStoryRepository.class);
        epicMapper = mock(EpicMapper.class);

        epicService = new EpicServiceImpl(epicRepository, productBacklogRepository, userStoryRepository, epicMapper);
    }

    @Test
    void testCreateEpic() {
        EpicDTO dto = new EpicDTO();
        dto.setTitre("Epic 1");
        dto.setDescription("Description");
        dto.setProductBacklogId(1L);
        dto.setUserStoryIds(List.of(2L, 3L));

        Epic epicEntity = new Epic();
        Epic savedEpic = new Epic();
        savedEpic.setId(10L);
        EpicDTO savedDto = new EpicDTO();
        savedDto.setId(10L);

        when(epicMapper.toEntity(dto)).thenReturn(epicEntity);

        ProductBacklog pb = new ProductBacklog();
        when(productBacklogRepository.findById(1L)).thenReturn(Optional.of(pb));

        List<UserStory> userStories = Arrays.asList(new UserStory(), new UserStory());
        when(userStoryRepository.findAllById(dto.getUserStoryIds())).thenReturn(userStories);

        when(epicRepository.save(epicEntity)).thenReturn(savedEpic);
        when(epicMapper.toDTO(savedEpic)).thenReturn(savedDto);

        EpicDTO result = epicService.create(dto);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        verify(epicRepository).save(epicEntity);
    }

    @Test
    void testGetEpicById_NotFound() {
        when(epicRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> epicService.getById(99L));
    }

    @Test
    void testGetEpicById_Success() {
        Epic epic = new Epic();
        epic.setId(1L);
        EpicDTO dto = new EpicDTO();
        dto.setId(1L);

        when(epicRepository.findById(1L)).thenReturn(Optional.of(epic));
        when(epicMapper.toDTO(epic)).thenReturn(dto);

        EpicDTO result = epicService.getById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void testGetAll() {
        List<Epic> epics = Arrays.asList(new Epic(), new Epic());
        when(epicRepository.findAll()).thenReturn(epics);
        when(epicMapper.toDTO(any(Epic.class))).thenReturn(new EpicDTO());

        List<EpicDTO> result = epicService.getAll();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdateEpic_NotFound() {
        when(epicRepository.findById(1L)).thenReturn(Optional.empty());

        EpicDTO dto = new EpicDTO();
        assertThrows(EntityNotFoundException.class, () -> epicService.update(1L, dto));
    }

    @Test
    void testDeleteEpic_NotFound() {
        when(epicRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> epicService.delete(1L));
    }

    @Test
    void testDeleteEpic_Success() {
        when(epicRepository.existsById(1L)).thenReturn(true);

        epicService.delete(1L);
        verify(epicRepository).deleteById(1L);
    }
}

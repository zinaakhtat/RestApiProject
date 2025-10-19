package com.AppsAgile.RestApi.servicestest;

import com.AppsAgile.RestApi.Dto.ProductBacklogDTO;
import com.AppsAgile.RestApi.Entities.Epic;
import com.AppsAgile.RestApi.Entities.ProductBacklog;
import com.AppsAgile.RestApi.Entities.UserStory;
import com.AppsAgile.RestApi.Repo.EpicRepository;
import com.AppsAgile.RestApi.Repo.ProductBacklogRepository;
import com.AppsAgile.RestApi.Repo.UserStoryRepository;
import com.AppsAgile.RestApi.Services.ProductBacklogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductBacklogServiceImplTest {

    private ProductBacklogRepository productBacklogRepository;
    private UserStoryRepository userStoryRepository;
    private EpicRepository epicRepository;

    private ProductBacklogServiceImpl service;

    @BeforeEach
    void setUp() {
        productBacklogRepository = mock(ProductBacklogRepository.class);
        userStoryRepository = mock(UserStoryRepository.class);
        epicRepository = mock(EpicRepository.class);
        service = new ProductBacklogServiceImpl(productBacklogRepository, userStoryRepository, epicRepository);
    }

    @Test
    void testCreateProductBacklog() {
        ProductBacklogDTO dto = new ProductBacklogDTO();
        dto.setNom("PB 1");
        dto.setUserStoryIds(List.of(1L, 2L));
        dto.setEpicIds(List.of(3L));

        List<UserStory> mockUserStories = List.of(new UserStory(), new UserStory());
        List<Epic> mockEpics = List.of(new Epic());

        when(userStoryRepository.findAllById(dto.getUserStoryIds())).thenReturn(mockUserStories);
        when(epicRepository.findAllById(dto.getEpicIds())).thenReturn(mockEpics);

        ProductBacklog savedEntity = ProductBacklog.builder().id(1L).nom("PB 1").userStories(mockUserStories).epics(mockEpics).build();
        when(productBacklogRepository.save(any())).thenReturn(savedEntity);

        ProductBacklogDTO result = service.create(dto);

        assertThat(result.getNom()).isEqualTo("PB 1");
        verify(productBacklogRepository).save(any());
    }

    @Test
    void testGetByIdSuccess() {
        ProductBacklog pb = ProductBacklog.builder().id(1L).nom("PB test").build();
        when(productBacklogRepository.findById(1L)).thenReturn(Optional.of(pb));

        ProductBacklogDTO result = service.getById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("PB test");
    }

    @Test
    void testGetByIdThrowsException() {
        when(productBacklogRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getById(1L));
    }

    @Test
    void testGetAll() {
        when(productBacklogRepository.findAll()).thenReturn(List.of(
                ProductBacklog.builder().id(1L).nom("PB1").build(),
                ProductBacklog.builder().id(2L).nom("PB2").build()
        ));

        List<ProductBacklogDTO> result = service.getAll();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNom()).isEqualTo("PB1");
    }

    @Test
    void testUpdateProductBacklog() {
        ProductBacklogDTO dto = new ProductBacklogDTO();
        dto.setNom("Updated PB");
        dto.setUserStoryIds(List.of(1L));
        dto.setEpicIds(List.of(2L));

        ProductBacklog existing = ProductBacklog.builder().id(1L).nom("Old PB").build();

        when(productBacklogRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userStoryRepository.findAllById(List.of(1L))).thenReturn(List.of(new UserStory()));
        when(epicRepository.findAllById(List.of(2L))).thenReturn(List.of(new Epic()));
        when(productBacklogRepository.save(any())).thenReturn(existing);

        ProductBacklogDTO result = service.update(1L, dto);

        assertThat(result.getNom()).isEqualTo("Updated PB");
        verify(productBacklogRepository).save(existing);
    }

    @Test
    void testDeleteProductBacklog() {
        when(productBacklogRepository.existsById(1L)).thenReturn(true);

        service.delete(1L);

        verify(productBacklogRepository).deleteById(1L);
    }

    @Test
    void testDeleteProductBacklogNotFound() {
        when(productBacklogRepository.existsById(999L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> service.delete(999L));
    }
}

package com.AppsAgile.RestApi.servicestest;

import com.AppsAgile.RestApi.Dto.SprintBacklogDTO;
import com.AppsAgile.RestApi.DtoMappers.SprintBacklogMapper;
import com.AppsAgile.RestApi.Entities.SprintBacklog;
import com.AppsAgile.RestApi.Entities.Task;
import com.AppsAgile.RestApi.Entities.UserStory;
import com.AppsAgile.RestApi.Repo.SprintBacklogRepository;
import com.AppsAgile.RestApi.Repo.TaskRepository;
import com.AppsAgile.RestApi.Repo.UserStoryRepository;
import com.AppsAgile.RestApi.Services.SprintBacklogServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SprintBacklogServiceImplTest {

    @Mock
    private SprintBacklogRepository sprintBacklogRepository;

    @Mock
    private UserStoryRepository userStoryRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private SprintBacklogServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        SprintBacklogDTO dto = new SprintBacklogDTO();
        dto.setNom("Sprint 1");
        dto.setDatedebut(LocalDate.now());
        dto.setDatefin(LocalDate.now().plusDays(14));
        dto.setUserStoryIds(List.of(1L, 2L));
        dto.setTaskIds(List.of(3L, 4L));

        List<UserStory> userStories = List.of(new UserStory(1L, null, null, null, null, null, null, null, null));
        List<Task> tasks = List.of(new Task(3L, "T1", "desc", null, null, null));

        when(userStoryRepository.findAllById(dto.getUserStoryIds())).thenReturn(userStories);
        when(taskRepository.findAllById(dto.getTaskIds())).thenReturn(tasks);

        SprintBacklog saved = new SprintBacklog(1L, dto.getNom(), dto.getDatedebut(), dto.getDatefin(), userStories, tasks);

        when(sprintBacklogRepository.save(any())).thenReturn(saved);

        SprintBacklogDTO result = service.create(dto);

        assertEquals(dto.getNom(), result.getNom());
        verify(sprintBacklogRepository, times(1)).save(any());
    }

    @Test
    void testGetById_whenExists() {
        SprintBacklog entity = new SprintBacklog(1L, "Sprint 1", LocalDate.now(), LocalDate.now().plusDays(10), new ArrayList<>(), new ArrayList<>());
        when(sprintBacklogRepository.findById(1L)).thenReturn(Optional.of(entity));

        SprintBacklogDTO result = service.getById(1L);

        assertEquals("Sprint 1", result.getNom());
    }

    @Test
    void testGetById_whenNotExists() {
        when(sprintBacklogRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getById(1L));
    }

    @Test
    void testGetAll() {
        List<SprintBacklog> list = List.of(
                new SprintBacklog(1L, "Sprint A", LocalDate.now(), LocalDate.now().plusDays(1), new ArrayList<>(), new ArrayList<>()),
                new SprintBacklog(2L, "Sprint B", LocalDate.now(), LocalDate.now().plusDays(2), new ArrayList<>(), new ArrayList<>())
        );

        when(sprintBacklogRepository.findAll()).thenReturn(list);

        List<SprintBacklogDTO> result = service.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void testUpdate_whenExists() {
        Long id = 1L;
        SprintBacklog existing = new SprintBacklog(id, "Old Name", LocalDate.now(), LocalDate.now().plusDays(5), new ArrayList<>(), new ArrayList<>());
        when(sprintBacklogRepository.findById(id)).thenReturn(Optional.of(existing));

        SprintBacklogDTO dto = new SprintBacklogDTO();
        dto.setNom("Updated Name");

        SprintBacklog updated = new SprintBacklog(id, "Updated Name", LocalDate.now(), LocalDate.now().plusDays(5), new ArrayList<>(), new ArrayList<>());

        when(sprintBacklogRepository.save(existing)).thenReturn(updated);

        SprintBacklogDTO result = service.update(id, dto);

        assertEquals("Updated Name", result.getNom());
        verify(sprintBacklogRepository).save(existing);
    }

    @Test
    void testUpdate_whenNotExists() {
        when(sprintBacklogRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.update(1L, new SprintBacklogDTO()));
    }

    @Test
    void testDelete_whenExists() {
        when(sprintBacklogRepository.existsById(1L)).thenReturn(true);
        service.delete(1L);
        verify(sprintBacklogRepository).deleteById(1L);
    }

    @Test
    void testDelete_whenNotExists() {
        when(sprintBacklogRepository.existsById(1L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> service.delete(1L));
    }
}

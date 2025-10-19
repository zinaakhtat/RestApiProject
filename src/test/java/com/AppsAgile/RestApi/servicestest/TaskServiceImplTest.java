package com.AppsAgile.RestApi.servicestest;

import com.AppsAgile.RestApi.Dto.TaskDTO;
import com.AppsAgile.RestApi.DtoMappers.TaskMapper;
import com.AppsAgile.RestApi.Entities.Task;
import com.AppsAgile.RestApi.Enumurations.Statut;
import com.AppsAgile.RestApi.Repo.TaskRepository;
import com.AppsAgile.RestApi.Services.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    private TaskDTO taskDTO;
    private Task task;

    @BeforeEach
    void setUp() {
        taskDTO = new TaskDTO();
        taskDTO.setId(1L);
        taskDTO.setTitre("Test Task");
        taskDTO.setDescription("This is a test task");
        taskDTO.setStatut(Statut.TO_DO);

        task = new Task();
        task.setId(1L);
        task.setTitre("Test Task");
        task.setDescription("This is a test task");
        task.setStatut(Statut.TO_DO);
    }

    @Test
    void testCreateTask() {
        when(taskMapper.toEntity(taskDTO)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.toDTO(task)).thenReturn(taskDTO);

        TaskDTO result = taskService.createTask(taskDTO);

        assertEquals(taskDTO, result);
        verify(taskRepository).save(task);
    }

    @Test
    void testUpdateTask() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        doAnswer(invocation -> {
            TaskDTO dto = invocation.getArgument(0);
            Task entity = invocation.getArgument(1);
            entity.setTitre(dto.getTitre());
            return null;
        }).when(taskMapper).updateEntityFromDTO(any(TaskDTO.class), any(Task.class));
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.toDTO(task)).thenReturn(taskDTO);

        TaskDTO result = taskService.updateTask(1L, taskDTO);

        assertEquals(taskDTO.getTitre(), result.getTitre());
        verify(taskRepository).save(task);
    }

    @Test
    void testGetTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskMapper.toDTO(task)).thenReturn(taskDTO);

        TaskDTO result = taskService.getTaskById(1L);

        assertEquals(taskDTO, result);
        verify(taskRepository).findById(1L);
    }

    @Test
    void testGetAllTasks() {
        List<Task> tasks = Collections.singletonList(task);
        when(taskRepository.findAll()).thenReturn(tasks);
        when(taskMapper.toDTO(task)).thenReturn(taskDTO);

        List<TaskDTO> result = taskService.getAllTasks();

        assertEquals(1, result.size());
        assertEquals(taskDTO, result.getFirst());
    }

    @Test
    void testDeleteTask() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        taskService.deleteTask(1L);

        verify(taskRepository).delete(task);
    }

    @Test
    void testGetTaskById_NotFound() {
        when(taskRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            taskService.getTaskById(2L);
        });

        assertEquals("Task not found", exception.getMessage());
    }
}

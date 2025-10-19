package com.AppsAgile.RestApi.servicestest;

import com.AppsAgile.RestApi.Dto.DescriptionDTO;
import com.AppsAgile.RestApi.DtoMappers.DescriptionMapper;
import com.AppsAgile.RestApi.Entities.Description;
import com.AppsAgile.RestApi.Repo.DescriptionRepository;
import com.AppsAgile.RestApi.Services.DescriptionServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DescriptionServiceImplTest {

    @Mock
    private DescriptionRepository descriptionRepository;

    @InjectMocks
    private DescriptionServiceImpl descriptionService;

    private Description description;
    private DescriptionDTO descriptionDTO;

    @BeforeEach
    void setUp() {
        description = new Description(1L, "Role 1", "Besoin 1", "Raison 1");
        descriptionDTO = new DescriptionDTO(1L, "Role 1", "Besoin 1", "Raison 1");
    }

    @Test
    void testCreateDescription() {
        // Arrange
        when(descriptionRepository.save(any(Description.class))).thenReturn(description);

        // Act
        DescriptionDTO createdDescription = descriptionService.create(descriptionDTO);

        // Assert
        assertNotNull(createdDescription);
        assertEquals(descriptionDTO.getRole(), createdDescription.getRole());
        assertEquals(descriptionDTO.getBesoin(), createdDescription.getBesoin());
        assertEquals(descriptionDTO.getRaison(), createdDescription.getRaison());
    }

    @Test
    void testGetDescriptionById() {
        // Arrange
        when(descriptionRepository.findById(1L)).thenReturn(Optional.of(description));

        // Act
        DescriptionDTO result = descriptionService.getById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Role 1", result.getRole());
        assertEquals("Besoin 1", result.getBesoin());
        assertEquals("Raison 1", result.getRaison());
    }

    @Test
    void testGetDescriptionById_ThrowsEntityNotFoundException() {
        // Arrange
        when(descriptionRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> descriptionService.getById(1L));
    }

    @Test
    void testGetAllDescriptions() {
        // Arrange
        List<Description> descriptions = Arrays.asList(
                new Description(1L, "Role 1", "Besoin 1", "Raison 1"),
                new Description(2L, "Role 2", "Besoin 2", "Raison 2")
        );
        when(descriptionRepository.findAll()).thenReturn(descriptions);

        // Act
        List<DescriptionDTO> result = descriptionService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Role 1", result.get(0).getRole());
        assertEquals("Role 2", result.get(1).getRole());
    }

    @Test
    void testUpdateDescription() {
        // Arrange
        Description updatedDescription = new Description(1L, "Updated Role", "Updated Besoin", "Updated Raison");
        when(descriptionRepository.findById(1L)).thenReturn(Optional.of(description));
        when(descriptionRepository.save(any(Description.class))).thenReturn(updatedDescription);

        // Act
        DescriptionDTO result = descriptionService.update(1L, descriptionDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Role", result.getRole());
        assertEquals("Updated Besoin", result.getBesoin());
        assertEquals("Updated Raison", result.getRaison());
    }

    @Test
    void testUpdateDescription_ThrowsEntityNotFoundException() {
        // Arrange
        when(descriptionRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> descriptionService.update(1L, descriptionDTO));
    }

    @Test
    void testDeleteDescription() {
        // Arrange
        when(descriptionRepository.existsById(1L)).thenReturn(true);
        doNothing().when(descriptionRepository).deleteById(1L);

        // Act
        descriptionService.delete(1L);

        // Assert
        verify(descriptionRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteDescription_ThrowsEntityNotFoundException() {
        // Arrange
        when(descriptionRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> descriptionService.delete(1L));
    }
}

package com.AppsAgile.RestApi.Controller;

import com.AppsAgile.RestApi.Dto.SprintBacklogDTO;
import com.AppsAgile.RestApi.Services.SprintBacklogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprint-backlogs")
public class SprintBacklogController {

    private final SprintBacklogService sprintBacklogService;

    public SprintBacklogController(SprintBacklogService sprintBacklogService) {
        this.sprintBacklogService = sprintBacklogService;
    }

    // Créer un nouveau SprintBacklog
    @PostMapping
    public ResponseEntity<SprintBacklogDTO> create(@RequestBody SprintBacklogDTO sprintBacklogDTO) {
        SprintBacklogDTO createdSprintBacklog = sprintBacklogService.create(sprintBacklogDTO);
        return new ResponseEntity<>(createdSprintBacklog, HttpStatus.CREATED);
    }

    // Récupérer un SprintBacklog par son ID
    @GetMapping("/{id}")
    public ResponseEntity<SprintBacklogDTO> getById(@PathVariable Long id) {
        SprintBacklogDTO sprintBacklogDTO = sprintBacklogService.getById(id);
        return new ResponseEntity<>(sprintBacklogDTO, HttpStatus.OK);
    }

    // Récupérer tous les SprintBacklogs
    @GetMapping
    public ResponseEntity<List<SprintBacklogDTO>> getAll() {
        List<SprintBacklogDTO> sprintBacklogs = sprintBacklogService.getAll();
        return new ResponseEntity<>(sprintBacklogs, HttpStatus.OK);
    }

    // Mettre à jour un SprintBacklog par son ID
    @PutMapping("/{id}")
    public ResponseEntity<SprintBacklogDTO> update(@PathVariable Long id, @RequestBody SprintBacklogDTO sprintBacklogDTO) {
        SprintBacklogDTO updatedSprintBacklog = sprintBacklogService.update(id, sprintBacklogDTO);
        return new ResponseEntity<>(updatedSprintBacklog, HttpStatus.OK);
    }

    // Supprimer un SprintBacklog par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sprintBacklogService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

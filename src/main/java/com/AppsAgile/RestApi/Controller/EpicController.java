package com.AppsAgile.RestApi.Controller;

import com.AppsAgile.RestApi.Dto.EpicDTO;
import com.AppsAgile.RestApi.Services.EpicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/epics")
public class EpicController {

    private final EpicService epicService;

    public EpicController(EpicService epicService) {
        this.epicService = epicService;
    }

    // Créer une nouvelle Epic
    @PostMapping
    public ResponseEntity<EpicDTO> create(@RequestBody EpicDTO epicDTO) {
        EpicDTO createdEpic = epicService.create(epicDTO);
        return new ResponseEntity<>(createdEpic, HttpStatus.CREATED);
    }

    // Récupérer une Epic par son ID
    @GetMapping("/{id}")
    public ResponseEntity<EpicDTO> getById(@PathVariable Long id) {
        EpicDTO epicDTO = epicService.getById(id);
        return new ResponseEntity<>(epicDTO, HttpStatus.OK);
    }

    // Récupérer toutes les Epics
    @GetMapping
    public ResponseEntity<List<EpicDTO>> getAll() {
        List<EpicDTO> epics = epicService.getAll();
        return new ResponseEntity<>(epics, HttpStatus.OK);
    }

    // Mettre à jour une Epic par son ID
    @PutMapping("/{id}")
    public ResponseEntity<EpicDTO> update(@PathVariable Long id, @RequestBody EpicDTO epicDTO) {
        EpicDTO updatedEpic = epicService.update(id, epicDTO);
        return new ResponseEntity<>(updatedEpic, HttpStatus.OK);
    }

    // Supprimer une Epic par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        epicService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

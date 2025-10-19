package com.AppsAgile.RestApi.Controller;

import com.AppsAgile.RestApi.Dto.DescriptionDTO;
import com.AppsAgile.RestApi.Services.DescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/descriptions")
public class DescriptionController {

    private final DescriptionService descriptionService;

    public DescriptionController(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    // Créer une nouvelle description
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DescriptionDTO createDescription(@RequestBody DescriptionDTO descriptionDTO) {
        return descriptionService.create(descriptionDTO);
    }

    // Récupérer une description par son ID
    @GetMapping("/{id}")
    public DescriptionDTO getDescriptionById(@PathVariable Long id) {
        return descriptionService.getById(id);
    }

    // Récupérer toutes les descriptions
    @GetMapping
    public List<DescriptionDTO> getAllDescriptions() {
        return descriptionService.getAll();
    }

    // Mettre à jour une description existante
    @PutMapping("/{id}")
    public DescriptionDTO updateDescription(@PathVariable Long id, @RequestBody DescriptionDTO descriptionDTO) {
        return descriptionService.update(id, descriptionDTO);
    }

    // Supprimer une description par son ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDescription(@PathVariable Long id) {
        descriptionService.delete(id);
    }
}

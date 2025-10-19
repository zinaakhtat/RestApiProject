package com.AppsAgile.RestApi.Controller;

import com.AppsAgile.RestApi.Dto.ProductBacklogDTO;
import com.AppsAgile.RestApi.Services.ProductBacklogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-backlogs")
public class ProductBacklogController {

    private final ProductBacklogService productBacklogService;

    public ProductBacklogController(ProductBacklogService productBacklogService) {
        this.productBacklogService = productBacklogService;
    }

    // Créer un nouveau ProductBacklog
    @PostMapping
    public ResponseEntity<ProductBacklogDTO> create(@RequestBody ProductBacklogDTO productBacklogDTO) {
        ProductBacklogDTO createdProductBacklog = productBacklogService.create(productBacklogDTO);
        return new ResponseEntity<>(createdProductBacklog, HttpStatus.CREATED);
    }

    // Récupérer un ProductBacklog par son ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductBacklogDTO> getById(@PathVariable Long id) {
        ProductBacklogDTO productBacklogDTO = productBacklogService.getById(id);
        return new ResponseEntity<>(productBacklogDTO, HttpStatus.OK);
    }

    // Récupérer tous les ProductBacklogs
    @GetMapping
    public ResponseEntity<List<ProductBacklogDTO>> getAll() {
        List<ProductBacklogDTO> productBacklogs = productBacklogService.getAll();
        return new ResponseEntity<>(productBacklogs, HttpStatus.OK);
    }

    // Mettre à jour un ProductBacklog par son ID
    @PutMapping("/{id}")
    public ResponseEntity<ProductBacklogDTO> update(@PathVariable Long id, @RequestBody ProductBacklogDTO productBacklogDTO) {
        ProductBacklogDTO updatedProductBacklog = productBacklogService.update(id, productBacklogDTO);
        return new ResponseEntity<>(updatedProductBacklog, HttpStatus.OK);
    }

    // Supprimer un ProductBacklog par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productBacklogService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

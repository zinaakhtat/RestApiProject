package com.AppsAgile.RestApi.Controller;

import com.AppsAgile.RestApi.Dto.UserStoryDTO;
import com.AppsAgile.RestApi.Services.UserStoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-stories")
public class UserStoryController {

    private final UserStoryServiceImpl userStoryService;

    public UserStoryController(UserStoryServiceImpl userStoryService) {
        this.userStoryService = userStoryService;
    }

    // ➕ Créer une User Story
    @PostMapping
    public ResponseEntity<UserStoryDTO> createUserStory(@RequestBody UserStoryDTO dto) {
        UserStoryDTO created = userStoryService.saveUserStory(dto);
        return ResponseEntity.ok(created);
    }

    // 🔄 Modifier une User Story
    @PutMapping("/{id}")
    public ResponseEntity<UserStoryDTO> updateUserStory(@PathVariable Long id, @RequestBody UserStoryDTO dto) {
        UserStoryDTO updated = userStoryService.updateUserStory(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserStory(@PathVariable Long id) {
        userStoryService.deleteUserStory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserStoryDTO> getUserStoryById(@PathVariable Long id) {
        UserStoryDTO dto = userStoryService.getUserStoryById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<UserStoryDTO>> getAllUserStories() {
        List<UserStoryDTO> list = userStoryService.getAllUserStories();
        return ResponseEntity.ok(list);
    }
}

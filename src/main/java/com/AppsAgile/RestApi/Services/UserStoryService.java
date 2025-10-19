package com.AppsAgile.RestApi.Services;

import com.AppsAgile.RestApi.Dto.UserStoryDTO;
import java.util.List;
public interface UserStoryService {
    UserStoryDTO saveUserStory(UserStoryDTO userStoryDTO);
    UserStoryDTO updateUserStory(Long id, UserStoryDTO userStoryDTO);
    void deleteUserStory(Long id);
    UserStoryDTO getUserStoryById(Long id);
    List<UserStoryDTO> getAllUserStories();
}

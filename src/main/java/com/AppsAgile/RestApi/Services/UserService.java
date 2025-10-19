package com.AppsAgile.RestApi.Services;


import com.AppsAgile.RestApi.Entities.User;
import com.AppsAgile.RestApi.Enumurations.Role;
import com.AppsAgile.RestApi.Security.DTO.AuthResponse;
import com.AppsAgile.RestApi.Security.DTO.LoginRequest;

public interface UserService {
    AuthResponse login(LoginRequest request);
    public User register(User user);
    public void deleteUser(Long id);
    public void updateUser(Long id, User updatedUser);
    public void updateUserRole(Long id, Role role);
}

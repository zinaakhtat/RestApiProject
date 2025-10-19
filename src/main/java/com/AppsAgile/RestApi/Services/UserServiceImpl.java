package com.AppsAgile.RestApi.Services;

import com.AppsAgile.RestApi.Entities.User;
import com.AppsAgile.RestApi.Enumurations.Role;
import com.AppsAgile.RestApi.Repo.UserRepository;
import com.AppsAgile.RestApi.Security.DTO.AuthResponse;
import com.AppsAgile.RestApi.Security.DTO.LoginRequest;
import com.AppsAgile.RestApi.Security.JWT.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'email : " + request.getEmail()));

        // Change RuntimeException to a more appropriate exception, e.g., InvalidCredentialsException
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswd())) {
            throw new EntityNotFoundException("Mot de passe incorrect pour l'utilisateur : " + request.getEmail());
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }

    @Transactional
    public User register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPasswd());
        user.setPasswd(encodedPassword);
        return userRepository.save(user);
    }

    public void updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + id));

        user.setEmail(updatedUser.getEmail());
        user.setName(updatedUser.getName());
        user.setPasswd(passwordEncoder.encode(updatedUser.getPasswd()));  // Use the injected passwordEncoder
        user.setRole(updatedUser.getRole());

        userRepository.save(user);
    }

    public void updateUserRole(Long id, Role role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + id));

        user.setRole(role);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + id));

        userRepository.delete(user);
    }
}

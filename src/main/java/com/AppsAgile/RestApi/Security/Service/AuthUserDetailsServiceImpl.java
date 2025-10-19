package com.AppsAgile.RestApi.Security.Service;

import com.AppsAgile.RestApi.Entities.User;
import com.AppsAgile.RestApi.Repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthUserDetails loadUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new AuthUserDetails(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByEmail(username);
    }
}

package com.AppsAgile.RestApi.Security.Service;

import com.AppsAgile.RestApi.Entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthUserDetails implements UserDetails {

    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPasswd(); // Assure-toi que ton getter s'appelle bien comme ça
        this.authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Permet d'activer le compte
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Permet d'éviter les blocages
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Mot de passe encore valide
    }

    @Override
    public boolean isEnabled() {
        return true; // Le compte est actif
    }
}


package com.AppsAgile.RestApi.Security.Config;

import com.AppsAgile.RestApi.Enumurations.Role;
import com.AppsAgile.RestApi.Security.JWT.JwtFilter;
import com.AppsAgile.RestApi.Security.JWT.JwtUtil;
import com.AppsAgile.RestApi.Security.Service.AuthUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthUserDetailsServiceImpl authUserDetailsService;
    private final JwtUtil jwtUtil;

    public SecurityConfig(AuthUserDetailsServiceImpl authUserDetailsService, JwtUtil jwtUtil) {
        this.authUserDetailsService = authUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return authUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(authUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtUtil, authUserDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login","/error").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/api/users/**").permitAll()

                        // Developer: tasks
                        .requestMatchers(HttpMethod.GET, "/api/tasks/**")
                        .hasAuthority(Role.DEVELOPER.name())

                        // Scrum Manager:   sprintbacklogs
                        .requestMatchers(HttpMethod.GET, "/api/sprint-backlogs/**")
                        .hasAnyAuthority(Role.SCRUM_MANAGER.name())

                        // Product Manager: user stories, product backlogs, epics
                        .requestMatchers(HttpMethod.GET, "/api/user-stories/**", "/api/product-backlogs/**", "/api/epics/**")
                        .hasAuthority(Role.PRODUCT_OWNER.name())


                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
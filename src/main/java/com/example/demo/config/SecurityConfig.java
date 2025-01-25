package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * Define a PasswordEncoder bean to be used for encoding and decoding passwords.
     * BCrypt is a strong hashing function recommended for password storage.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure Spring Security settings for the application.
     *
     * - Disable CSRF for APIs (common in stateless applications).
     * - Permit public access to specific endpoints (e.g., register).
     * - Protect all other endpoints with authentication.
     * - Use HTTP Basic authentication for simplicity.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for API endpoints
            .authorizeHttpRequests()
            .requestMatchers("/api/auth/register", "/api/auth/login").permitAll() // Publicly accessible endpoints
            .anyRequest().authenticated() // Protect all other endpoints
            .and()
            .httpBasic(); // Use HTTP Basic authentication for simplicity

        return http.build();
    }
}

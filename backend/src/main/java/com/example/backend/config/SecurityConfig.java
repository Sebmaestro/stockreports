
package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Inaktivera CSRF-skydd
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Tillåt alla requests, oavsett endpoint
            )
            .formLogin(form -> form.disable()) // Inaktivera form login
            .httpBasic(httpBasic -> httpBasic.disable()); // Inaktivera basic auth

        return http.build();
    }
}
    

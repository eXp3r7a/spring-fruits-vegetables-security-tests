package com.example.spring_validation_fruits_vegetables.config;

import com.example.spring_validation_fruits_vegetables.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/products/get", "/recipes/get").permitAll()
                        .requestMatchers("/products/add", "/recipes/add").authenticated()
                        .requestMatchers("/products/submit", "/recipe/submit").authenticated()
                        .requestMatchers("/products/edit", "/recipes/edit").authenticated()
                        .requestMatchers("/products/update", "/recipes/update").authenticated()
                        .requestMatchers("/products/delete", "/recipes/delete").authenticated()
                        .requestMatchers("/results","/profile").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
}

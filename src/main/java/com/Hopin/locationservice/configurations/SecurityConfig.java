package com.Hopin.locationservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/location/**","/api/location/nearby/drivers/**"))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/location/**","/api/location/nearby/drivers/**" ).permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }
}

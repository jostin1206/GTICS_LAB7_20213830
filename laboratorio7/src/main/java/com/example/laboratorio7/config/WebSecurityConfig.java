package com.example.laboratorio7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .requestMatchers("/listaProyecciones", "/employee/**").authenticated()
               // .requestMatchers("/shipper", "/shipper/**").authenticated()
                .anyRequest().permitAll();
        return http.build();

    }

}

package com.MarvelDemo.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @EnableMethodSecurity(securedEnabled = true)
    public class SecurityConfig {

        @Autowired
        private  JwtFilter jwtFilter;
        @Autowired
        private Cors corsConfigurationSource;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            http
                    .csrf(csrf -> csrf.disable())
                    .cors(cors -> cors.configurationSource(corsConfigurationSource.corsConfigurationSource()))
                    .authorizeHttpRequests(requests -> requests
                            .requestMatchers(HttpMethod.POST, "/marvel/api/v1/auth/register").permitAll()
                            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // Permitir recursos estáticos
                            .anyRequest().permitAll()
                    )
                    .addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
            return configuration.getAuthenticationManager();
        }
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }




    }

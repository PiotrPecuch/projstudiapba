package com.projpba.projpba.Security.Services;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SpringSecurityConfig {
    private UserDetailsService userDetailsService;

    @Bean
    static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf( csrf -> {
            csrf.disable();
                })
                .authorizeHttpRequests((authorize -> {
                    authorize.requestMatchers("/auth/**").permitAll();
                    authorize.requestMatchers("/hotel/**").permitAll();
                    authorize.requestMatchers("/forgot_password/**").permitAll();
                    authorize.requestMatchers("/room/reservation/**").permitAll();
                    authorize.anyRequest().authenticated();
                }));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}

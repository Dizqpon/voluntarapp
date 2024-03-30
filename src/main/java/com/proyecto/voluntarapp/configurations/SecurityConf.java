package com.proyecto.voluntarapp.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.proyecto.voluntarapp.auth.CustomAuthenticationSuccessHandler;
import com.proyecto.voluntarapp.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConf {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csfr -> csfr.disable())
            .authorizeHttpRequests(authRequest ->
                authRequest
                    .requestMatchers("/auth/**", "/", "/index.html", "/css/**", "/js/**", "/images/**").permitAll()
                    .requestMatchers("/**").hasRole("ADMIN")
                    .requestMatchers("/voluntario/**").hasRole("VOLUNTARIO")
                    .requestMatchers("/necesitado/**").hasRole("NECESITADO")
                    .anyRequest().authenticated())
            .sessionManagement(sessionManager ->
                sessionManager
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin(formLogin -> formLogin.successHandler(customAuthenticationSuccessHandler))
            .build();
    }
}

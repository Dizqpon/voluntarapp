package com.proyecto.voluntarapp.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.proyecto.voluntarapp.auth.AuthResponse;
import com.proyecto.voluntarapp.auth.LoginRequest;
import com.proyecto.voluntarapp.auth.RegisterRequest;
import com.proyecto.voluntarapp.models.Usuario;
import com.proyecto.voluntarapp.repositories.UsuarioRepository;
import com.proyecto.voluntarapp.services.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    

    
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {        
        return ResponseEntity.ok(authService.register(request));
    }

    
}

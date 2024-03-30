package com.proyecto.voluntarapp.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.voluntarapp.auth.AuthResponse;
import com.proyecto.voluntarapp.auth.LoginRequest;
import com.proyecto.voluntarapp.auth.RegisterRequest;
import com.proyecto.voluntarapp.models.Rol;
import com.proyecto.voluntarapp.models.Usuario;
import com.proyecto.voluntarapp.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(usuario);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @SuppressWarnings("null")
    public AuthResponse register(RegisterRequest request) {
        Usuario usuario = Usuario.builder()
                            .email(request.getEmail())
                            .password(passwordEncoder.encode(request.getPassword()))
                            .nombre(request.getNombre())
                            .apellido(request.getApellido())
                            .phonenumber(request.getPhonenumber())
                            .comunidad(request.getComunidad())
                            .tipo_ayuda(request.getTipo_ayuda())
                            .rol(Rol.ADMINISTRADOR)
                            .build();
        usuarioRepository.save(usuario);

        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }

}

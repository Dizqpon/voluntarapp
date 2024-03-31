package com.proyecto.voluntarapp.controllers;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.voluntarapp.auth.LoginRequest;
import com.proyecto.voluntarapp.models.Usuario;
import com.proyecto.voluntarapp.repositories.UsuarioRepository;
import com.proyecto.voluntarapp.services.AuthService;

@Controller
@RequiredArgsConstructor
public class IndexController {
    @Autowired
    public UsuarioRepository usuarioRepository;
    @Autowired
    private final AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String getRaiz(Model model) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(152L);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            model.addAttribute("usuario", usuario);
            // Haz algo con el usuario
        } else {
            // Maneja el caso en que el usuario no se encuentra
        }
        model.addAttribute("titulo", "VoluntarApp-Home");
        return "index";
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        model.addAttribute("titulo", "VoluntarApp-Home");
        return "index";
    }

    @GetMapping("/auth/login")
    public String getLogin(Model model) {
        return "/auth/login";
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        // Autenticar al usuario utilizando el servicio AuthService
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        // Verificar si la autenticación fue exitosa
        if (authentication.isAuthenticated()) {
            // Obtener el usuario autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Generar el token de autenticación
            String token = authService.login(new LoginRequest(email, password)).getToken();

            // Determinar la URL de redirección según el rol del usuario
            String redirectUrl = determineRedirectUrl(userDetails.getAuthorities());

            // Acceder a diferentes atributos del usuario
            String username = userDetails.getUsername();
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            boolean accountNonExpired = userDetails.isAccountNonExpired();
            boolean accountNonLocked = userDetails.isAccountNonLocked();
            boolean credentialsNonExpired = userDetails.isCredentialsNonExpired();
            boolean enabled = userDetails.isEnabled();

            System.out.println("Email:");
            System.out.println(username);
            System.out.println("Autorities");
            System.out.println(authorities);
            System.out.println("accountNonExpired");
            System.out.println(accountNonExpired);
            System.out.println("accountNonLocked");
            System.out.println(accountNonLocked);
            System.out.println("credentialsNonExpired");
            System.out.println(credentialsNonExpired);
            System.out.println("enabled");
            System.out.println(enabled);
            // Devolver una redirección HTTP directa
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", redirectUrl);
            headers.add("Authorization", "Bearer " + token);
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } else {
            // Si la autenticación falla, devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación");
        }
    }

    private String determineRedirectUrl(Collection<? extends GrantedAuthority> authorities) {
        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();
            if ("ADMINISTRADOR".equals(role)) {
                return "/admin/admin";
            } else if ("VOLUNTARIO".equals(role)) {
                return "/voluntario/index_vol";
            } else if ("NECESITADO".equals(role)) {
                return "/necesitado/index_nec";
            }
        }
        // Si ningún rol coincide, redirigir a la página principal o a una página de
        // error
        return "/";
    }

    @GetMapping("/auth/register")
    public String register(Model model) {
        model.addAttribute("titulo", "VoluntarApp-Register");
        return "/auth/register";
    }

    @GetMapping("/admin/admin")
    public String getIndexAdm() {
        return "/admin/admin";
    }

    @PostMapping("/admin/admin")
    public String postIndexAdm() {
        return "/admin/admin";
    }

    @GetMapping("/voluntario/index_vol")
    public String getIndexVol() {
        return "voluntario/index_vol";
    }

    @GetMapping("/necesitado/index_nec")
    public String getIndexNec() {
        return "necesitado/index_nec";
    }
}

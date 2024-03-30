package com.proyecto.voluntarapp.controllers;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.voluntarapp.models.Usuario;
import com.proyecto.voluntarapp.repositories.UsuarioRepository;

@Controller
@RequiredArgsConstructor
public class IndexController {
    @Autowired
    public UsuarioRepository usuarioRepository;

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
    public String login(Model model) {
        model.addAttribute("titulo", "VoluntarApp-Register");
        return "/auth/login";
    }

    @GetMapping("/auth/register")
    public String register(Model model) {
        model.addAttribute("titulo", "VoluntarApp-Register");
        return "/auth/register";
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

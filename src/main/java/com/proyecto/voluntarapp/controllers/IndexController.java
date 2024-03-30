package com.proyecto.voluntarapp.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequiredArgsConstructor
public class IndexController {
    
    @GetMapping("/")
    public String getRaiz(Model model) {
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
        model.addAttribute("titulo", "VoluntarApp-Login");
        return "login";
    }
    
    @GetMapping("/auth/register")
    public String register(Model model) {
        model.addAttribute("titulo", "VoluntarApp-Register");
        return "register";
    }

}

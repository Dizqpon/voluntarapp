package com.proyecto.voluntarapp.controllers;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppController {
    @PostMapping(value = "index_nec")
    public String index_nec() {
        //TODO: process POST request
        
        return "Hola desde el index Necesitado";
    }
    @PostMapping("/index_vol")
    public String index_vol() {
        //TODO: process POST request
        
        return "index_vol";
    }
    
    @PostMapping("/index_adm")
    public String index_adm() {
        //TODO: process POST request
        
        return "index_adm";
    }
}

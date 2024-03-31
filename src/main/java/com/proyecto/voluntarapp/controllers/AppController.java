package com.proyecto.voluntarapp.controllers;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppController {

    @GetMapping("/index_nec")
    public String index_nec() {
        //TODO: process POST request
        
        return "index";
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

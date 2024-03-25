package com.proyecto.voluntarapp.models;


public enum Rol {
    VOLUNTARIO("Voluntario"),
    NECESITADO("Necesitado"),
    ADMINISTRADOR("Administrador");

    private String nombre;

    Rol(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

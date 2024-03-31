package com.proyecto.voluntarapp.models;

public enum Rol {
    VOLUNTARIO("VOLUNTARIO"),
    NECESITADO("NECESITADO"),
    ADMINISTRADOR("ADMINISTRADOR");

    private final String rol;

    Rol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }
}

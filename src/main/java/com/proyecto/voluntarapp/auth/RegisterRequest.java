package com.proyecto.voluntarapp.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String email;
    String password;
    String nombre;
    String apellido;
    String phonenumber;
    String comunidad;
    String tipo_ayuda;
}

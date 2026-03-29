package com.finanzas.finanzas_backend.Dto;

import java.time.Instant;

import com.finanzas.finanzas_backend.Models.Usuario;

public record UsuarioPublico(String id, String nombre, String email, String rol, Instant fechaCreacion) {

    public static UsuarioPublico desde(Usuario u) {
        return new UsuarioPublico(u.getId(), u.getNombre(), u.getEmail(), u.getRol(), u.getFechaCreacion());
    }
}

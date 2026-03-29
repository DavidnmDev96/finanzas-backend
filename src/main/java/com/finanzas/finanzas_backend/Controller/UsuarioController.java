package com.finanzas.finanzas_backend.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finanzas.finanzas_backend.Dto.UsuarioPublico;
import com.finanzas.finanzas_backend.Services.UsuarioService;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/api/usuarios")
    public List<UsuarioPublico> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }
}
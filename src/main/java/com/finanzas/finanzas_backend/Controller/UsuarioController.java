package com.finanzas.finanzas_backend.Controller;

import com.finanzas.finanzas_backend.Models.Usuario;
import com.finanzas.finanzas_backend.Services.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/api/usuarios")
    public List<Usuario> obtenerTodos() {
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        System.out.println("Usuarios encontrados: " + usuarios.size());
        return usuarios;
    }
}
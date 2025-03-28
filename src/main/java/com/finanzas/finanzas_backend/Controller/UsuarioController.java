package com.finanzas.finanzas_backend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    // Endpoint básico
    @GetMapping("/api/usuarios")
    public String obtenerUsuarios() {
        return "¡La API de Finanzas está funcionando correctamente!";
    }
}
package com.finanzas.finanzas_backend.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finanzas.finanzas_backend.Dto.UsuarioPublico;
import com.finanzas.finanzas_backend.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioPublico> obtenerTodos() {
        return usuarioRepository.findAll().stream().map(UsuarioPublico::desde).toList();
    }
}

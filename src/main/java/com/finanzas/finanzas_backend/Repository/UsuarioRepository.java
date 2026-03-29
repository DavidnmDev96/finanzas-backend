package com.finanzas.finanzas_backend.Repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.finanzas.finanzas_backend.Models.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNombre(String nombre);
}
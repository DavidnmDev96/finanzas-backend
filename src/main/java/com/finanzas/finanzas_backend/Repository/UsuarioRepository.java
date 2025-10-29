package com.finanzas.finanzas_backend.Repository;

import com.finanzas.finanzas_backend.Models.Usuario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

}
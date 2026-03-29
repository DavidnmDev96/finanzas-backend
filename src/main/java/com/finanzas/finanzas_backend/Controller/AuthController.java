package com.finanzas.finanzas_backend.Controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finanzas.finanzas_backend.Dto.LoginRequest;
import com.finanzas.finanzas_backend.Dto.LoginResponse;
import com.finanzas.finanzas_backend.Models.Usuario;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.finanzas.finanzas_backend.Security.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final String COLECCION_USUARIOS = "usuarios";

    private final MongoTemplate mongoTemplate;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(MongoTemplate mongoTemplate, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.mongoTemplate = mongoTemplate;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (request == null || request.getUsuario() == null || request.getUsuario().isBlank()
                || request.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Faltan usuario o password en el JSON");
        }
        String clave = request.getUsuario().trim();
        Optional<Usuario> usuario = buscarUsuarioParaLogin(clave);
        if (usuario.isEmpty() || !coincidePassword(request.getPassword().trim(), usuario.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
        }

        String token = jwtService.generateToken(usuario.get().getEmail());
        return ResponseEntity.ok(new LoginResponse(token, jwtService.getExpirationMs()));
    }

    private boolean coincidePassword(String raw, String almacenada) {
        if (almacenada == null) {
            return false;
        }
        if (almacenada.startsWith("$2a$") || almacenada.startsWith("$2b$") || almacenada.startsWith("$2y$")) {
            return passwordEncoder.matches(raw, almacenada);
        }
        return raw.equals(almacenada);
    }

    private Optional<Usuario> buscarUsuarioParaLogin(String clave) {
        Usuario porEmail = mongoTemplate.findOne(
                Query.query(Criteria.where("email").is(clave)), Usuario.class, COLECCION_USUARIOS);
        if (porEmail != null) {
            return Optional.of(porEmail);
        }
        Usuario porNombre = mongoTemplate.findOne(
                Query.query(Criteria.where("nombre").is(clave)), Usuario.class, COLECCION_USUARIOS);
        return Optional.ofNullable(porNombre);
    }
}

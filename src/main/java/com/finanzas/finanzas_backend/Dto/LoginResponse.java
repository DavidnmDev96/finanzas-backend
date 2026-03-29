package com.finanzas.finanzas_backend.Dto;

public class LoginResponse {
    private final String token;
    private final String tipo;
    private final long expiraEnMs;

    public LoginResponse(String token, long expiraEnMs) {
        this.token = token;
        this.tipo = "Bearer";
        this.expiraEnMs = expiraEnMs;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }

    public long getExpiraEnMs() {
        return expiraEnMs;
    }
}

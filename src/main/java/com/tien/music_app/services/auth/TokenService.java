package com.tien.music_app.services.auth;

import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;

public interface TokenService {
    String generate(String username, String role, int expiration);
    String refresh(String refreshToken);
    Claims getClaims(String token);
    SecretKey encodeSecretKey();
}

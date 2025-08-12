package com.tien.music_app.services.auth;

import com.tien.music_app.Enums.ERole;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.util.UUID;

public interface TokenService {
    String generate(String id, ERole role, int expiration);
    String refresh(String refreshToken);
    Claims getClaims(String token);
    SecretKey encodeSecretKey();
}

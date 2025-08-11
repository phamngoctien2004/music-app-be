package com.tien.music_app.services.auth;

import com.tien.music_app.dtos.request.AuthRequest;
import com.tien.music_app.dtos.response.AuthResponse;

public interface AuthProvider {
    boolean supports(String provider);
    AuthResponse authenticate(AuthRequest request);
}

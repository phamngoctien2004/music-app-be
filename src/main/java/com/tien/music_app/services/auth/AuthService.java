package com.tien.music_app.services.auth;

import com.tien.music_app.dtos.request.AuthRequest;
import com.tien.music_app.dtos.response.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);

}

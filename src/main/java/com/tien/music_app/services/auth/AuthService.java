package com.tien.music_app.services.auth;

import com.tien.music_app.dtos.response.LoginResponse;

public interface AuthService {
    LoginResponse authenticate();
}

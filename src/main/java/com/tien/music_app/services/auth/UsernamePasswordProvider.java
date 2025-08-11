package com.tien.music_app.services.auth;

import com.tien.music_app.dtos.request.AuthRequest;
import com.tien.music_app.dtos.response.AuthResponse;
import com.tien.music_app.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
public class UsernamePasswordProvider implements  AuthProvider{
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public boolean supports(String provider) {
        return "username_password".equals(provider);
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
//      kiem tra da ton tai
//      Kiem tra mat khau
        return null;
    }
}

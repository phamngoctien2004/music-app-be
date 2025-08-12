package com.tien.music_app.services.auth;

import com.tien.music_app.dtos.request.AuthRequest;
import com.tien.music_app.dtos.request.RegisterRequest;
import com.tien.music_app.dtos.request.ResetPassword;
import com.tien.music_app.dtos.response.AuthResponse;
import com.tien.music_app.exceptions.AppException;
import com.tien.music_app.exceptions.ErrorCode;
import com.tien.music_app.mappers.mapper.UserMapper;
import com.tien.music_app.models.User;
import com.tien.music_app.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsernamePasswordProvider implements AuthProvider, UsernamePasswordService {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    @Override
    public boolean supports(String provider) {
        return "username_password".equals(provider);
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.AUTH_FAILED));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.AUTH_FAILED);
        }
        return AuthResponse.builder()
                .accessToken(tokenService.generate(user.getId().toString(), user.getRole().getCode(), 15))
                .refreshToken(tokenService.generate(user.getId().toString(), user.getRole().getCode(), 15000))
                .userResponse(userMapper.toResponse(user))
                .build();
    }
    @Override
    public String resetPassword(ResetPassword request) {
        return null;
    }

    @Override
    public String sendResetPassword(String email) {
        return null;
    }

    @Override
    public String register(RegisterRequest request) {
        return null;
    }
}

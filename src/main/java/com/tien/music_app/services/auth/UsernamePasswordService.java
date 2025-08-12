package com.tien.music_app.services.auth;

import com.tien.music_app.dtos.request.RegisterRequest;
import com.tien.music_app.dtos.request.ResetPassword;

public interface UsernamePasswordService {
    String resetPassword(ResetPassword request);
    String sendResetPassword(String email);
    String register(RegisterRequest request);
}

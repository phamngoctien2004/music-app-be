package com.tien.music_app.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
//  info user
    private UserResponse userResponse;
}

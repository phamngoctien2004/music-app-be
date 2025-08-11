package com.tien.music_app.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String accessToken;
    private boolean newAccount;
}

package com.tien.music_app.dtos.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}

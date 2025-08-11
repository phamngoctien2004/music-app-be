package com.tien.music_app.dtos.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;

//  Google OAuth
    private String code;
    private String access_token;
    private String name;
    private String picture;

//   type support
    private String providerType;
}

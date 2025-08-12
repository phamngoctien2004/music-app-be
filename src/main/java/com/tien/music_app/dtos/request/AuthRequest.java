package com.tien.music_app.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank
    private String password;

//  Google OAuth
    private String code;
    private String access_token;
    private String name;
    private String picture;

//   type support
    private String providerType;
}

package com.tien.music_app.dtos.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String id;
    private String password;
    private String retypePassword;
}

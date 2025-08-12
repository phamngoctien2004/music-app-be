package com.tien.music_app.dtos.request;

import lombok.Data;

@Data
public class ResetPassword {
    private String tokenReset;
    private String newPassword;
}

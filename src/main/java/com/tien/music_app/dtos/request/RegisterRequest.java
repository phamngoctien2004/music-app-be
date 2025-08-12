package com.tien.music_app.dtos.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private final String email;
    private final String name;

}

package com.tien.music_app.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoogleRequest {
    private String code;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String grantType;
}

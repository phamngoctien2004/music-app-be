package com.tien.music_app.services.other;

import com.tien.music_app.dtos.request.AuthRequest;
import com.tien.music_app.dtos.request.GoogleRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpService {
    public AuthRequest get(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                "https://openidconnect.googleapis.com/v1/userinfo",
                HttpMethod.GET,
                httpEntity,
                AuthRequest.class
        ).getBody();
    }

//
    public String post(GoogleRequest body, String url) {
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GoogleRequest> httpEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(
                url,
                httpEntity,
                GoogleRequest.class
        ).getBody().getAccess_token();
    }
}

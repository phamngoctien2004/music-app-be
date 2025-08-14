package com.tien.music_app.services.auth;

public interface GoogleService {
    String getLink();
    void handleCallback(String code);
}

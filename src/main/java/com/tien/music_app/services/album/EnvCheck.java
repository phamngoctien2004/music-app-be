package com.tien.music_app.services.album;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class EnvCheck implements CommandLineRunner {
    @Value("${GOOGLE_CLIENT_ID}")
    private String clientId;

    @Override public void run(String... args) {
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalStateException("Missing GOOGLE_CLIENT_ID/GOOGLE_CLIENT_SECRET");
        }
        System.out.println(clientId);
    }
}
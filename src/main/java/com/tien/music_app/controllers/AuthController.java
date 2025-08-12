package com.tien.music_app.controllers;

import com.tien.music_app.dtos.request.AuthRequest;
import com.tien.music_app.dtos.response.AuthResponse;
import com.tien.music_app.services.auth.AuthService;
import com.tien.music_app.services.auth.GoogleProvider;
import com.tien.music_app.services.auth.GoogleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final GoogleService googleService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest){
        return ResponseEntity.ok(authService.login(authRequest));
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok("f");
    }

    @GetMapping("/google-link")
    public ResponseEntity<String> googleLink() {
        return ResponseEntity.ok(googleService.getLink());
    }
    @GetMapping("/google-callback")
    public ResponseEntity<AuthResponse> googleCallback(@RequestParam String code) {
        AuthRequest request = new AuthRequest();
        request.setCode(code);
        request.setProviderType("google");
        AuthResponse response = authService.login(request);
//      save cookie
        ResponseCookie cookie = ResponseCookie.from("refreshToken", response.getRefreshToken())
                .httpOnly(true)
                .path("/")
                .maxAge(Duration.ofDays(300))
                .sameSite("Strict")
                .secure(true)
                .build();

        return ResponseEntity.ok(response);
    }

}

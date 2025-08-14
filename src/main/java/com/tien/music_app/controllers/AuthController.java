package com.tien.music_app.controllers;

import com.tien.music_app.dtos.request.AuthRequest;
import com.tien.music_app.dtos.response.AuthResponse;
import com.tien.music_app.services.auth.AuthService;
import com.tien.music_app.services.auth.GoogleProvider;
import com.tien.music_app.services.auth.GoogleService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Duration;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final GoogleService googleService;

    @Value("${FE_URL}")
    private String frontendUrl;

    public AuthController(AuthService authService, GoogleService googleService) {
        this.authService = authService;
        this.googleService = googleService;
    }

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
    public void googleCallback(@RequestParam String code, HttpServletResponse response) throws IOException {
        googleService.handleCallback(code);
        response.sendRedirect(frontendUrl + "/login?code=" + code);
    }

    @PostMapping("/google-login")
    public ResponseEntity<AuthResponse> loginGoogle(@RequestBody AuthRequest request){
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

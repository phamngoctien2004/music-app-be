package com.tien.music_app.services.auth;

import com.tien.music_app.dtos.request.AuthRequest;
import com.tien.music_app.dtos.response.AuthResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final List<AuthProvider> authProviders;

    public AuthServiceImpl(List<AuthProvider> authProviders) {
        this.authProviders = authProviders;
    }

//  choose provider support
    @Override
    public AuthResponse login(AuthRequest request) {
        return authProviders.stream()
                .filter(provider -> provider.supports(request.getProviderType()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Unsupported provider: " + request.getProviderType()))
                .authenticate(request);
    }
}

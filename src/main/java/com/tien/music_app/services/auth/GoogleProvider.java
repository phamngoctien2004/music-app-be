package com.tien.music_app.services.auth;

import com.tien.music_app.dtos.request.AuthRequest;
import com.tien.music_app.dtos.request.GoogleRequest;
import com.tien.music_app.dtos.response.AuthResponse;
import com.tien.music_app.exceptions.AppException;
import com.tien.music_app.exceptions.ErrorCode;
import com.tien.music_app.mappers.mapper.UserMapper;
import com.tien.music_app.models.User;
import com.tien.music_app.services.other.HttpService;
import com.tien.music_app.services.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class GoogleProvider implements AuthProvider, GoogleService{
    @Value("${spring.security.oauth2.resourceserver.jwt.secret-key}")
    public String secretKey;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    public String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    public String clientSecret;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    public String redirectURI;

    @Value("${spring.security.oauth2.client.registration.google.scope}")
    public String scope;

    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    public String tokenURI;

    @Value("${spring.security.oauth2.client.provider.google.user-info-uri}")
    public String userInfoURI;
    @Override
    public boolean supports(String provider) {
        return "google".equals(provider);
    }
    private final HttpService httpService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenService tokenService;
    private final RedisTemplate<String, Object> redisTemplate;
    public GoogleProvider(
            HttpService httpService,
            UserService userService,
            UserMapper userMapper,
            TokenService tokenService,
            RedisTemplate<String, Object> redisTemplate
    ){
        this.httpService = httpService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
        this.redisTemplate = redisTemplate;
    }
    @Override
    @Transactional
    public AuthResponse authenticate(AuthRequest request) {
        String code = URLDecoder.decode(request.getCode(),StandardCharsets.UTF_8);
        Object codeIsNull = redisTemplate.opsForValue().get("google:code:" + code);
        if(codeIsNull == null){
            throw new AppException(ErrorCode.AUTH_FAILED);
        }
        String accessToken = this.getAccessToken(code);
        AuthRequest authRequest = this.getRequestInfo(accessToken);
        User user  = userService.findByEmail(authRequest.getEmail())
                .orElse(userService.createUserFromOauth(authRequest));

        return  AuthResponse.builder()
                .accessToken(tokenService.generate(user.getId().toString(), user.getRole().getCode(), 15))
                .refreshToken(tokenService.generate(user.getId().toString(), user.getRole().getCode(), 15000))
                .userResponse(userMapper.toResponse(user))
                .build();
    }



    @Override
    public String getLink() {
        return UriComponentsBuilder
                .fromPath("https://accounts.google.com/o/oauth2/v2/auth")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectURI)
                .queryParam("response_type", "code")
                .queryParam("scope", scope)
                .queryParam("state", UUID.randomUUID().toString())
                .queryParam("access_type", "online")
                .queryParam("prompt", "consent")
                .build()
                .toUriString();
    }

    @Override
    public void handleCallback(String code) {
        redisTemplate.opsForValue().set("google:code:" + code, code);
    }

    public AuthRequest getRequestInfo(String accessToken) {
        return httpService.get(accessToken);
    }
    public String getAccessToken(String code){
        GoogleRequest googleRequest = GoogleRequest.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .code(code)
                .redirectUri(redirectURI)
                .grantType("authorization_code")
                .build();
        return httpService.post(googleRequest, tokenURI);
    }
}

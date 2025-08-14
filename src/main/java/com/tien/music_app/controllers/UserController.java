package com.tien.music_app.controllers;

import com.tien.music_app.dtos.response.UserResponse;
import com.tien.music_app.services.auth.UsernamePasswordService;
import com.tien.music_app.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/me")
    ResponseEntity<UserResponse> me(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        return ResponseEntity.ok(userService.findById(id));
    }
}

package com.tien.music_app.services.user;

import com.tien.music_app.dtos.request.AuthRequest;
import com.tien.music_app.dtos.request.ChangePasswordRequest;
import com.tien.music_app.dtos.request.UserRequest;
import com.tien.music_app.dtos.response.UserResponse;
import com.tien.music_app.models.User;

import java.util.Optional;

public interface UserService {
//  Search user
    Optional<User> findByEmail(String email);
    UserResponse findById(String id);
    UserResponse me(String token);
//  create & update
    UserResponse createUser(UserRequest user);
    UserResponse updateUser(UserRequest user);
    User createUserFromOauth(AuthRequest request);

    String changePassword(ChangePasswordRequest request);
//  delete
    void deleteUser(String id);
}

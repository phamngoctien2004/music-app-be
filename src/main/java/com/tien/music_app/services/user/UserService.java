package com.tien.music_app.services.user;

import com.tien.music_app.dtos.request.GoogleRequest;
import com.tien.music_app.dtos.request.UserRequest;
import com.tien.music_app.dtos.response.UserResponse;

public interface UserService {
//  Search user
    UserResponse findByEmail(String email);
    UserResponse findById(Long id);

//  create & update
    UserResponse createUser(UserRequest user);
    UserResponse updateUser(UserRequest user);
    UserResponse createUserFromOauth(GoogleRequest request);


//  delete
    void deleteUser(String id);
}

package com.tien.music_app.services.user;

import com.tien.music_app.dtos.request.GoogleRequest;
import com.tien.music_app.dtos.request.UserRequest;
import com.tien.music_app.dtos.response.UserResponse;
import com.tien.music_app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public UserResponse findByEmail(String email) {
        return null;
    }

    @Override
    public UserResponse findById(Long id) {
        return null;
    }

    @Override
    public UserResponse createUser(UserRequest user) {
        return null;
    }

    @Override
    public UserResponse updateUser(UserRequest user) {
        return null;
    }

    @Override
    public UserResponse createUserFromOauth(GoogleRequest request) {
        return null;
    }

    @Override
    public void deleteUser(String id) {

    }
}

package com.tien.music_app.mappers.impl;

import com.tien.music_app.dtos.request.UserRequest;
import com.tien.music_app.dtos.response.UserResponse;
import com.tien.music_app.mappers.mapper.UserMapper;
import com.tien.music_app.mappers.mapstruct.UserMapstruct;
import com.tien.music_app.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserMapperImpl implements UserMapper {
    private final UserMapstruct mapstruct;
    @Override
    public UserResponse toResponse(User user) {

        return mapstruct.toResponse(user);
    }

    @Override
    public User toEntity(UserRequest request) {
        return mapstruct.toEntity(request);
    }
}

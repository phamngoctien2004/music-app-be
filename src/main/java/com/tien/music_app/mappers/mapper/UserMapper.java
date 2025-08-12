package com.tien.music_app.mappers.mapper;

import com.tien.music_app.dtos.request.UserRequest;
import com.tien.music_app.dtos.response.UserResponse;
import com.tien.music_app.models.User;

public interface UserMapper {
    UserResponse toResponse(User user);
    User toEntity(UserRequest request);


}

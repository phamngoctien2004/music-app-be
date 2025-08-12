package com.tien.music_app.mappers.mapstruct;

import com.tien.music_app.dtos.request.UserRequest;
import com.tien.music_app.dtos.response.UserResponse;
import com.tien.music_app.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapstruct {
    User toEntity(UserRequest dto);

    @Mapping(target = "role", source = "role.code")
    UserResponse toResponse(User entity);
}

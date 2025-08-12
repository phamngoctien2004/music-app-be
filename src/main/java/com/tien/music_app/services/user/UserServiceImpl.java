package com.tien.music_app.services.user;

import com.tien.music_app.Enums.ERole;
import com.tien.music_app.dtos.request.AuthRequest;
import com.tien.music_app.dtos.request.ChangePasswordRequest;
import com.tien.music_app.dtos.request.GoogleRequest;
import com.tien.music_app.dtos.request.UserRequest;
import com.tien.music_app.dtos.response.UserResponse;
import com.tien.music_app.exceptions.AppException;
import com.tien.music_app.exceptions.ErrorCode;
import com.tien.music_app.mappers.mapper.UserMapper;
import com.tien.music_app.models.User;
import com.tien.music_app.repository.UserRepository;
import com.tien.music_app.services.role.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public UserResponse findById(String id) {
        return userMapper.toResponse(
                repository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED))
        );
    }

    //  create new user
    @Override
    public UserResponse createUser(UserRequest request) {
        Optional<User> oldUser = repository.findByEmail(request.getEmail());

        if (oldUser.isPresent()) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toEntity(request);
        user.setRole(roleService.findByCode(ERole.USER));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toResponse(
                repository.save(user)
        );
    }

    //  update user info only
    @Override
    public UserResponse updateUser(UserRequest request) {
        User oldUser = repository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        oldUser.setName(request.getName());
        oldUser.setBirth(request.getBirth());
        oldUser.setGender(request.getGender());

        if (request.getAvatar() != null) {
            oldUser.setAvatar(request.getAvatar());
        }
        return userMapper.toResponse(
                repository.save(oldUser)
        );
    }

    //  create user when login with google
    @Override
    public User createUserFromOauth(AuthRequest request) {
        Optional<User> oldUser = repository.findByEmail(request.getEmail());

        if (oldUser.isEmpty()) {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            user.setAvatar(request.getPicture());
            user.setRole(roleService.findByCode(ERole.USER));
            return repository.save(user);
        }

        return oldUser.get();
    }

    @Override
    public String changePassword(ChangePasswordRequest request) {
        User user = repository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return "Password changed successfully";
    }

    @Override
    public void deleteUser(String id) {
        User oldUser = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        repository.delete(oldUser);
    }
}

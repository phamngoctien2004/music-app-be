package com.tien.music_app.services.role;

import com.tien.music_app.Enums.ERole;
import com.tien.music_app.exceptions.AppException;
import com.tien.music_app.exceptions.ErrorCode;
import com.tien.music_app.models.Role;
import com.tien.music_app.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository repository;
    @Override
    public Role findByCode(ERole code) {
        return repository.findByCode(code).orElseThrow(
                () -> new AppException(ErrorCode.ROLE_NOT_FOUND)
        );
    }
}

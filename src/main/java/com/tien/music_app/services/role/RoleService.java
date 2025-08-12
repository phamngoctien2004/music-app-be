package com.tien.music_app.services.role;

import com.tien.music_app.Enums.ERole;
import com.tien.music_app.models.Role;

public interface RoleService {
    Role findByCode(ERole code);
}

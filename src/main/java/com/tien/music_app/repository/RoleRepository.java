package com.tien.music_app.repository;

import com.tien.music_app.Enums.ERole;
import com.tien.music_app.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByCode(ERole code);
}

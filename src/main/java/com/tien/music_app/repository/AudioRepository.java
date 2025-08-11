package com.tien.music_app.repository;

import com.tien.music_app.models.Audio;
import com.tien.music_app.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AudioRepository extends JpaRepository<Audio, UUID> {
}

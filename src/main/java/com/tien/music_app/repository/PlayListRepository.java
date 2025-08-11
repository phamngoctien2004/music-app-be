package com.tien.music_app.repository;

import com.tien.music_app.models.Artist;
import com.tien.music_app.models.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList, UUID> {
}


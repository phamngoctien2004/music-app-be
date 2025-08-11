package com.tien.music_app.repository;

import com.tien.music_app.models.Album;
import com.tien.music_app.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlbumRepository extends JpaRepository<Album, UUID> {
}

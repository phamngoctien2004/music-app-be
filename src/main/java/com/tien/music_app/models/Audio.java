package com.tien.music_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Audio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String avatar;
    private String url;

    @Column(name = "lyrics", columnDefinition = "TEXT")
    private String lyrics;
    private LocalDate createdAt;
    private long playCount;

    @OneToMany(mappedBy = "audio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlbumAudio> albumAudios;

    @OneToMany(mappedBy = "audio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayAudios> playListAudios;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Artist artist;
}

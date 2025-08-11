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
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String avatar;
    private String description;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "playList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayAudios> playListAudios;
}

package com.musicapp.crud.model;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity @Table(name = "liked_songs", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LikedSong {
    @EmbeddedId
    private LikedSongId id;
    @ManyToOne @MapsId("userId") @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne @MapsId("songId") @JoinColumn(name = "song_id")
    private Song song;
    private Instant likedAt;
}

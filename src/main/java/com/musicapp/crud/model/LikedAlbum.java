package com.musicapp.crud.model;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity @Table(name = "liked_albums", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LikedAlbum {
    @EmbeddedId
    private LikedAlbumId id;
    @ManyToOne @MapsId("userId") @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne @MapsId("albumId") @JoinColumn(name = "album_id")
    private Album album;
    private Instant likedAt;
}

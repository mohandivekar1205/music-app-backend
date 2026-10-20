package com.musicapp.crud.model;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity @Table(name = "liked_playlists", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LikedPlaylist {
    @EmbeddedId
    private LikedPlaylistId id;
    @ManyToOne @MapsId("userId") @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne @MapsId("playlistId") @JoinColumn(name = "playlist_id")
    private Playlist playlist;
    private Instant likedAt;
}

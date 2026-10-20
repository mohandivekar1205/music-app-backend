package com.musicapp.crud.model;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity @Table(name = "playlist_songs", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlaylistSong {
    @EmbeddedId
    private PlaylistSongId id;
    @ManyToOne @MapsId("playlistId") @JoinColumn(name = "playlist_id")
    private Playlist playlist;
    @ManyToOne @MapsId("songId") @JoinColumn(name = "song_id")
    private Song song;
    private Integer position;
    private Instant addedAt;
}

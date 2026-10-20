package com.musicapp.crud.model;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;

@Entity @Table(name = "song_artists", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SongArtist {
    @EmbeddedId
    private SongArtistId id;
    @ManyToOne @MapsId("songId") @JoinColumn(name = "song_id")
    private Song song;
    @ManyToOne @MapsId("artistId") @JoinColumn(name = "artist_id")
    private Artist artist;
}

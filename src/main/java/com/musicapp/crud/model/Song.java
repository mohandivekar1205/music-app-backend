package com.musicapp.crud.model;
import com.musicapp.crud.model.enums.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Table(name = "songs", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Song {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;
    private String title;
    private Integer duration;
    private Integer trackNumber;
    private String audioUrl;
    private String coverImage;
    private String lyricsUrl;
    @Enumerated(EnumType.STRING) private SongStatus status;
    private Instant createdAt;
    @ManyToOne @JoinColumn(name = "album_id")
    private Album album;
}

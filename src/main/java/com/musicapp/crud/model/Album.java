package com.musicapp.crud.model;
import com.musicapp.crud.model.enums.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.Instant;

@Entity @Table(name = "albums", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Album {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;
    private String title;
    private String coverImage;
    private LocalDate releaseDate;
    private Integer releaseYear;
    @Enumerated(EnumType.STRING) private AlbumType albumType;
    private String description;
    private Instant createdAt;
    @ManyToOne @JoinColumn(name = "artist_id")
    private Artist artist;
}

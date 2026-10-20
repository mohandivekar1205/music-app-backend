package com.musicapp.crud.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Table(name = "podcasts", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Podcast {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long podcastId;
    private String title;
    private String author;
    private String coverImage;
    private String description;
    private String category;
    private String language;
    private Instant createdAt;
}

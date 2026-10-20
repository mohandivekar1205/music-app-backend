package com.musicapp.crud.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Table(name = "podcast_episodes", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PodcastEpisode {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long episodeId;
    private String title;
    private String description;
    private Integer duration;
    private String audioUrl;
    private LocalDate releaseDate;
    private Integer episodeNumber;
    @ManyToOne @JoinColumn(name = "podcast_id")
    private Podcast podcast;
}

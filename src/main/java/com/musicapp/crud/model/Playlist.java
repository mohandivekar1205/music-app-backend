package com.musicapp.crud.model;
import com.musicapp.crud.model.enums.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Table(name = "playlists", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Playlist {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistId;
    private String name;
    private String description;
    private String coverImage;
    @Enumerated(EnumType.STRING) private VisibilityType visibility;
    private Instant createdAt;
    private Instant updatedAt;
    @ManyToOne @JoinColumn(name = "owner_id")
    private User owner;
}

package com.musicapp.crud.model;
import com.musicapp.crud.model.enums.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Table(name = "downloads", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Download {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long downloadId;
    @Enumerated(EnumType.STRING) private MediaType mediaType;
    private Long mediaId;
    private Instant downloadedAt;
    @Enumerated(EnumType.STRING) private DownloadStatus status;
    private Instant expiresAt;
    @ManyToOne @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne @JoinColumn(name = "device_id")
    private Device device;
}

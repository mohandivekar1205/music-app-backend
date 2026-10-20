package com.musicapp.crud.model;
import com.musicapp.crud.model.enums.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Table(name = "play_history", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlayHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;
    @Enumerated(EnumType.STRING) private MediaType mediaType;
    private Long mediaId;
    private Instant playedAt;
    private Integer playDuration;
    @ManyToOne @JoinColumn(name = "user_id")
    private User user;
}

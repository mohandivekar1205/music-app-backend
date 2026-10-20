package com.musicapp.crud.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Table(name = "search_history", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SearchHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long searchId;
    private String keyword;
    private Instant searchedAt;
    @ManyToOne @JoinColumn(name = "user_id")
    private User user;
}

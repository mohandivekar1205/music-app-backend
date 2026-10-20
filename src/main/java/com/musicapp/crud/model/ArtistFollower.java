package com.musicapp.crud.model;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity @Table(name = "artist_followers", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ArtistFollower {
    @EmbeddedId
    private ArtistFollowerId id;
    @ManyToOne @MapsId("artistId") @JoinColumn(name = "artist_id")
    private Artist artist;
    @ManyToOne @MapsId("userId") @JoinColumn(name = "user_id")
    private User user;
    private Instant followedAt;
}

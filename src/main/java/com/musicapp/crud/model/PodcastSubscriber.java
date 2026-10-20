package com.musicapp.crud.model;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity @Table(name = "podcast_subscribers", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PodcastSubscriber {
    @EmbeddedId
    private PodcastSubscriberId id;
    @ManyToOne @MapsId("userId") @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne @MapsId("podcastId") @JoinColumn(name = "podcast_id")
    private Podcast podcast;
    private Instant subscribedAt;
}

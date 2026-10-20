package com.musicapp.crud.model;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;
@Embeddable @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PodcastSubscriberId implements Serializable {
    private Long userId;
    private Long podcastId;
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PodcastSubscriberId that = (PodcastSubscriberId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(podcastId, that.podcastId);
    }
    @Override public int hashCode() { return Objects.hash(userId, podcastId); }
}

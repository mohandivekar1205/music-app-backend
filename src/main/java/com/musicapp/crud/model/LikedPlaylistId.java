package com.musicapp.crud.model;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;
@Embeddable @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LikedPlaylistId implements Serializable {
    private Long userId;
    private Long playlistId;
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikedPlaylistId that = (LikedPlaylistId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(playlistId, that.playlistId);
    }
    @Override public int hashCode() { return Objects.hash(userId, playlistId); }
}

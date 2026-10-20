package com.musicapp.crud.model;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;
@Embeddable @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LikedSongId implements Serializable {
    private Long userId;
    private Long songId;
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikedSongId that = (LikedSongId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(songId, that.songId);
    }
    @Override public int hashCode() { return Objects.hash(userId, songId); }
}

package com.musicapp.crud.model;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;
@Embeddable @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LikedAlbumId implements Serializable {
    private Long userId;
    private Long albumId;
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikedAlbumId that = (LikedAlbumId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(albumId, that.albumId);
    }
    @Override public int hashCode() { return Objects.hash(userId, albumId); }
}

package com.musicapp.crud.model;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;
@Embeddable @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ArtistFollowerId implements Serializable {
    private Long artistId;
    private Long userId;
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistFollowerId that = (ArtistFollowerId) o;
        return Objects.equals(artistId, that.artistId) && Objects.equals(userId, that.userId);
    }
    @Override public int hashCode() { return Objects.hash(artistId, userId); }
}

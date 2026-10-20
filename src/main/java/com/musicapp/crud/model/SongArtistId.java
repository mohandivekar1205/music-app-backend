package com.musicapp.crud.model;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;
@Embeddable @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SongArtistId implements Serializable {
    private Long songId;
    private Long artistId;
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongArtistId that = (SongArtistId) o;
        return Objects.equals(songId, that.songId) && Objects.equals(artistId, that.artistId);
    }
    @Override public int hashCode() { return Objects.hash(songId, artistId); }
}

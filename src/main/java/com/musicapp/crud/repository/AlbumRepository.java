package com.musicapp.crud.repository;
import com.musicapp.crud.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByArtist_ArtistId(Long artistId);
}

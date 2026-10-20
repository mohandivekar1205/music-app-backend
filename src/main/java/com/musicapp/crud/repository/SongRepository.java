package com.musicapp.crud.repository;
import com.musicapp.crud.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByAlbum_AlbumId(Long albumId);
    List<Song> findByTitleContainingIgnoreCase(String query);
}

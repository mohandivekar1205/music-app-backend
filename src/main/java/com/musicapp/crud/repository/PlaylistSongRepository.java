package com.musicapp.crud.repository;
import com.musicapp.crud.model.PlaylistSong;
import com.musicapp.crud.model.PlaylistSongId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, PlaylistSongId> {
    List<PlaylistSong> findByPlaylist_PlaylistIdOrderByPosition(Long playlistId);
}

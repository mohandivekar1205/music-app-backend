package com.musicapp.crud.repository;
import com.musicapp.crud.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByOwner_UserId(Long userId);
}

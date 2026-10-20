package com.musicapp.crud.repository;
import com.musicapp.crud.model.LikedSong;
import com.musicapp.crud.model.LikedSongId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface LikedSongRepository extends JpaRepository<LikedSong, LikedSongId> {
    List<LikedSong> findById_userId(Long userId);
    boolean existsById(LikedSongId id);
}

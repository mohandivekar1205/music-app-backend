package com.musicapp.crud.repository;
import com.musicapp.crud.model.LikedPlaylist;
import com.musicapp.crud.model.LikedPlaylistId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface LikedPlaylistRepository extends JpaRepository<LikedPlaylist, LikedPlaylistId> {
    List<LikedPlaylist> findById_userId(Long userId);
    boolean existsById(LikedPlaylistId id);
}

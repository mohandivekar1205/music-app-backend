package com.musicapp.crud.repository;
import com.musicapp.crud.model.LikedAlbum;
import com.musicapp.crud.model.LikedAlbumId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface LikedAlbumRepository extends JpaRepository<LikedAlbum, LikedAlbumId> {
    List<LikedAlbum> findById_userId(Long userId);
    boolean existsById(LikedAlbumId id);
}

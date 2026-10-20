package com.musicapp.crud.repository;
import com.musicapp.crud.model.ArtistFollower;
import com.musicapp.crud.model.ArtistFollowerId;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ArtistFollowerRepository extends JpaRepository<ArtistFollower, ArtistFollowerId> {
    boolean existsById(ArtistFollowerId id);
    long countById_ArtistId(Long artistId);
}

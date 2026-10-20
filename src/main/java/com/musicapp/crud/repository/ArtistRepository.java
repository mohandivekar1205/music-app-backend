package com.musicapp.crud.repository;
import com.musicapp.crud.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ArtistRepository extends JpaRepository<Artist, Long> {}

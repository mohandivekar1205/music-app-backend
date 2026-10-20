package com.musicapp.crud.repository;
import com.musicapp.crud.model.SongArtist;
import com.musicapp.crud.model.SongArtistId;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SongArtistRepository extends JpaRepository<SongArtist, SongArtistId> {}

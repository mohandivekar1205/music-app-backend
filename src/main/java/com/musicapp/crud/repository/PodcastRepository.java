package com.musicapp.crud.repository;
import com.musicapp.crud.model.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PodcastRepository extends JpaRepository<Podcast, Long> {}

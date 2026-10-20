package com.musicapp.crud.repository;
import com.musicapp.crud.model.PodcastEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PodcastEpisodeRepository extends JpaRepository<PodcastEpisode, Long> {}

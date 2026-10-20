package com.musicapp.crud.dto.response;
import java.time.LocalDate;
public record PodcastEpisodeResponse(
    Long episodeId, String title, String description, Integer duration,
    String audioUrl, LocalDate releaseDate, Integer episodeNumber, PodcastResponse podcast
) {}

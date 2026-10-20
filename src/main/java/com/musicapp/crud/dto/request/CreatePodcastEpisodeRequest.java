package com.musicapp.crud.dto.request;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
public record CreatePodcastEpisodeRequest(
    @NotBlank String title, String description, Integer duration,
    String audioUrl, LocalDate releaseDate, Integer episodeNumber
) {}

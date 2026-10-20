package com.musicapp.crud.dto.response;
import java.time.Instant;
public record PodcastResponse(
    Long podcastId, String title, String author, String coverImage,
    String description, String category, String language, Instant createdAt
) {}

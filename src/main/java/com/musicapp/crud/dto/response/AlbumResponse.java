package com.musicapp.crud.dto.response;
import java.time.LocalDate;
import java.time.Instant;
public record AlbumResponse(
    Long albumId, String title, String coverImage, LocalDate releaseDate,
    Integer releaseYear, String albumType, String description,
    Instant createdAt, ArtistSummary artist
) {}

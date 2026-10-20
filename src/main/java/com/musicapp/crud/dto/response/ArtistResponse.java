package com.musicapp.crud.dto.response;
import java.time.Instant;
public record ArtistResponse(
    Long artistId, String name, String coverImage, String biography,
    Boolean verified, Instant createdAt
) {}

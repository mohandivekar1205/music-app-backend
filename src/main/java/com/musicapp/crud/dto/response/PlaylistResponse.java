package com.musicapp.crud.dto.response;
import java.time.Instant;
import java.util.List;
public record PlaylistResponse(
    Long playlistId, String name, String description, String coverImage,
    String visibility, Instant createdAt, Instant updatedAt,
    UserResponse owner, List<SongResponse> songs
) {}

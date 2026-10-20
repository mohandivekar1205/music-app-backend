package com.musicapp.crud.dto.response;
import java.util.List;
public record SongResponse(
    Long songId, String title, Integer duration, Integer trackNumber,
    String audioUrl, String coverImage, String status,
    AlbumSummary album, List<ArtistSummary> artists
) {}

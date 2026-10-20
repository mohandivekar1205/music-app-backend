package com.musicapp.crud.dto.response;

import java.time.Instant;

public record FileUploadResponse(
    Long songId,
    String title,
    String audioUrl,
    String coverImage,
    String lyricsUrl,
    Integer duration,
    Integer trackNumber,
    String status,
    Long albumId,
    Instant createdAt,
    String uploadedFileUrl,
    String originalFileName,
    Long fileSize,
    String contentType,
    SongResponse song
) {}

package com.musicapp.crud.dto.response;
import java.time.Instant;
public record DownloadResponse(
    Long downloadId, String mediaType, Long mediaId,
    Instant downloadedAt, String status, Instant expiresAt
) {}

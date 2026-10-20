package com.musicapp.crud.dto.response;
import java.time.Instant;
public record PlayHistoryResponse(
    Long historyId, String mediaType, Long mediaId,
    Instant playedAt, Integer playDuration
) {}

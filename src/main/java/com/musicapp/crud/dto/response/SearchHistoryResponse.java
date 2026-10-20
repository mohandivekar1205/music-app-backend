package com.musicapp.crud.dto.response;
import java.time.Instant;
public record SearchHistoryResponse(
    Long searchId, String keyword, Instant searchedAt
) {}

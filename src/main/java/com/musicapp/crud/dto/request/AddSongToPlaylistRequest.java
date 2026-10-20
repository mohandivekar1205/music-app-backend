package com.musicapp.crud.dto.request;
import jakarta.validation.constraints.*;
public record AddSongToPlaylistRequest(
    @NotNull Long songId, Integer position
) {}

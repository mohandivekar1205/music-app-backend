package com.musicapp.crud.dto.request;
import jakarta.validation.constraints.*;
import com.musicapp.crud.model.enums.SongStatus;
public record UpdateSongAudioRequest(
    @NotBlank String audioUrl, @NotNull SongStatus status
) {}

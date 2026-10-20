package com.musicapp.crud.dto.request;
import jakarta.validation.constraints.*;
public record RegisterDeviceRequest(
    @NotBlank String deviceName, @NotBlank String platform, @NotBlank String deviceToken
) {}

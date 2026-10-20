package com.musicapp.crud.dto.response;
import java.time.Instant;
public record DeviceResponse(
    Long deviceId, String deviceName, String platform, Instant lastLogin
) {}

package com.musicapp.crud.dto.request;
public record UpdatePlaylistRequest(
    String name, String description, String coverImage, String visibility
) {}

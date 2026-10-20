package com.musicapp.crud.service;
import com.musicapp.crud.model.*;
import com.musicapp.crud.model.enums.*;
import com.musicapp.crud.repository.*;
import com.musicapp.crud.dto.request.*;
import com.musicapp.crud.dto.response.*;
import com.musicapp.crud.dto.Mapper;
import com.musicapp.crud.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.Instant;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongResponse createSong(CreateSongRequest req) {
        Album album = albumRepository.findById(req.albumId())
            .orElseThrow(() -> new ResourceNotFoundException("Album not found"));
        Song song = Song.builder()
            .title(req.title())
            .duration(req.duration())
            .trackNumber(req.trackNumber())
            .audioUrl(req.audioUrl())
            .coverImage(req.coverImage())
            .lyricsUrl(req.lyricsUrl())
            .status(req.status() != null ? SongStatus.valueOf(req.status()) : SongStatus.DRAFT)
            .createdAt(Instant.now())
            .album(album)
            .build();
        return Mapper.toResponse(songRepository.save(song), Collections.singletonList(album.getArtist()));
    }

    public SongResponse getSong(Long id) {
        Song song = songRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Song not found"));
        return Mapper.toResponse(song, Collections.singletonList(song.getAlbum().getArtist()));
    }

    public List<SongResponse> search(String query) {
        return songRepository.findByTitleContainingIgnoreCase(query).stream()
            .map(s -> Mapper.toResponse(s, Collections.singletonList(s.getAlbum().getArtist())))
            .collect(Collectors.toList());
    }

    public SongResponse updateAudio(Long songId, UpdateSongAudioRequest req) {
        Song song = songRepository.findById(songId)
            .orElseThrow(() -> new ResourceNotFoundException("Song not found"));
        song.setAudioUrl(req.audioUrl());
        song.setStatus(req.status());
        return Mapper.toResponse(songRepository.save(song), Collections.singletonList(song.getAlbum().getArtist()));
    }
}

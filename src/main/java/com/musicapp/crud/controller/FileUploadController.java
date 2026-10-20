package com.musicapp.crud.controller;

import com.musicapp.crud.dto.Mapper;
import com.musicapp.crud.dto.response.FileUploadResponse;
import com.musicapp.crud.dto.response.SongResponse;
import com.musicapp.crud.exception.ResourceNotFoundException;
import com.musicapp.crud.model.Album;
import com.musicapp.crud.model.Song;
import com.musicapp.crud.model.enums.SongStatus;
import com.musicapp.crud.repository.AlbumRepository;
import com.musicapp.crud.repository.SongRepository;
import com.musicapp.crud.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Collections;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Tag(name = "File Upload", description = "Endpoints for uploading files to Supabase Storage and storing metadata")
public class FileUploadController {

    private final StorageService storageService;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload file to Supabase Storage and save song metadata")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "albumId", required = false) Long albumId,
            @RequestParam(value = "duration", required = false) Integer duration,
            @RequestParam(value = "trackNumber", required = false) Integer trackNumber,
            @RequestParam(value = "coverImage", required = false) String coverImage,
            @RequestParam(value = "lyricsUrl", required = false) String lyricsUrl,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "fileType", required = false, defaultValue = "audio") String fileType,
            @RequestParam(value = "songId", required = false) Long songId
    ) {
        // 1. Determine folder based on file type
        String folder = "songs";
        if ("image".equalsIgnoreCase(fileType) || "cover".equalsIgnoreCase(fileType)) {
            folder = "covers";
        } else if ("lyrics".equalsIgnoreCase(fileType)) {
            folder = "lyrics";
        }

        // 2. Upload file to Supabase Storage
        String fileUrl = storageService.uploadFile(file, folder);

        // 3. Resolve Album if provided
        Album album = null;
        if (albumId != null) {
            album = albumRepository.findById(albumId)
                    .orElseThrow(() -> new ResourceNotFoundException("Album not found with id: " + albumId));
        }

        // 4. Resolve Song title (fallback to original filename without extension)
        String songTitle = title;
        if (songTitle == null || songTitle.trim().isEmpty()) {
            String originalName = file.getOriginalFilename() != null ? file.getOriginalFilename() : "Untitled";
            int dotIndex = originalName.lastIndexOf('.');
            songTitle = (dotIndex > 0) ? originalName.substring(0, dotIndex) : originalName;
        }

        // 5. Parse Status
        SongStatus songStatus = SongStatus.DRAFT;
        if (status != null && !status.trim().isEmpty()) {
            try {
                songStatus = SongStatus.valueOf(status.trim().toUpperCase());
            } catch (IllegalArgumentException ignored) {
                // Keep default DRAFT if invalid
            }
        }

        // 6. Create or update Song entity
        Song song;
        if (songId != null) {
            song = songRepository.findById(songId)
                    .orElseThrow(() -> new ResourceNotFoundException("Song not found with id: " + songId));
            if (title != null && !title.trim().isEmpty()) song.setTitle(songTitle);
            if (duration != null) song.setDuration(duration);
            if (trackNumber != null) song.setTrackNumber(trackNumber);
            if (status != null && !status.trim().isEmpty()) song.setStatus(songStatus);
            if (album != null) song.setAlbum(album);
        } else {
            song = Song.builder()
                    .title(songTitle)
                    .duration(duration)
                    .trackNumber(trackNumber)
                    .status(songStatus)
                    .album(album)
                    .createdAt(Instant.now())
                    .build();
        }

        // Assign file URL to appropriate field
        if ("image".equalsIgnoreCase(fileType) || "cover".equalsIgnoreCase(fileType)) {
            song.setCoverImage(fileUrl);
            if (lyricsUrl != null) song.setLyricsUrl(lyricsUrl);
        } else if ("lyrics".equalsIgnoreCase(fileType)) {
            song.setLyricsUrl(fileUrl);
            if (coverImage != null) song.setCoverImage(coverImage);
        } else {
            // Default: audio file
            song.setAudioUrl(fileUrl);
            if (coverImage != null) song.setCoverImage(coverImage);
            if (lyricsUrl != null) song.setLyricsUrl(lyricsUrl);
        }

        Song savedSong = songRepository.save(song);

        SongResponse songResponse = Mapper.toResponse(
                savedSong,
                (savedSong.getAlbum() != null && savedSong.getAlbum().getArtist() != null)
                        ? Collections.singletonList(savedSong.getAlbum().getArtist())
                        : Collections.emptyList()
        );

        FileUploadResponse response = new FileUploadResponse(
                savedSong.getSongId(),
                savedSong.getTitle(),
                savedSong.getAudioUrl(),
                savedSong.getCoverImage(),
                savedSong.getLyricsUrl(),
                savedSong.getDuration(),
                savedSong.getTrackNumber(),
                savedSong.getStatus() != null ? savedSong.getStatus().name() : null,
                savedSong.getAlbum() != null ? savedSong.getAlbum().getAlbumId() : null,
                savedSong.getCreatedAt(),
                fileUrl,
                file.getOriginalFilename(),
                file.getSize(),
                file.getContentType(),
                songResponse
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

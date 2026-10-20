package com.musicapp.crud.controller;
import com.musicapp.crud.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    private final LibraryService libraryService;
    public LibraryController(LibraryService libraryService) { this.libraryService = libraryService; }

    @PostMapping("/songs/{songId}/like")
    public ResponseEntity<Void> likeSong(@PathVariable Long songId, @RequestParam Long userId) {
        libraryService.likeSong(userId, songId);
        return ResponseEntity.status(201).build();
    }
    @DeleteMapping("/songs/{songId}/like")
    public ResponseEntity<Void> unlikeSong(@PathVariable Long songId, @RequestParam Long userId) {
        libraryService.unlikeSong(userId, songId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/albums/{albumId}/like")
    public ResponseEntity<Void> likeAlbum(@PathVariable Long albumId, @RequestParam Long userId) {
        libraryService.likeAlbum(userId, albumId);
        return ResponseEntity.status(201).build();
    }
    @DeleteMapping("/albums/{albumId}/like")
    public ResponseEntity<Void> unlikeAlbum(@PathVariable Long albumId, @RequestParam Long userId) {
        libraryService.unlikeAlbum(userId, albumId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/playlists/{playlistId}/like")
    public ResponseEntity<Void> likePlaylist(@PathVariable Long playlistId, @RequestParam Long userId) {
        libraryService.likePlaylist(userId, playlistId);
        return ResponseEntity.status(201).build();
    }
    @DeleteMapping("/playlists/{playlistId}/like")
    public ResponseEntity<Void> unlikePlaylist(@PathVariable Long playlistId, @RequestParam Long userId) {
        libraryService.unlikePlaylist(userId, playlistId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getUserLibrary(@PathVariable Long userId) {
        return ResponseEntity.ok(libraryService.getUserLibrary(userId));
    }
}

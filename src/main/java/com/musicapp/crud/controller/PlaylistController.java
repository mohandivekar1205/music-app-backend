package com.musicapp.crud.controller;
import com.musicapp.crud.service.PlaylistService;
import com.musicapp.crud.dto.request.*;
import com.musicapp.crud.dto.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;
    public PlaylistController(PlaylistService playlistService) { this.playlistService = playlistService; }

    @PostMapping("/")
    public ResponseEntity<PlaylistResponse> createPlaylist(@RequestParam Long userId, @Valid @RequestBody CreatePlaylistRequest req) {
        return ResponseEntity.status(201).body(playlistService.createPlaylist(userId, req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistResponse> getPlaylist(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.getPlaylist(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlaylistSummary>> getUserPlaylists(@PathVariable Long userId) {
        return ResponseEntity.ok(playlistService.getUserPlaylists(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaylistResponse> updatePlaylist(@PathVariable Long id, @Valid @RequestBody UpdatePlaylistRequest req) {
        return ResponseEntity.ok(playlistService.updatePlaylist(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/songs")
    public ResponseEntity<Void> addSong(@PathVariable Long id, @Valid @RequestBody AddSongToPlaylistRequest req) {
        playlistService.addSong(id, req);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}/songs/{songId}")
    public ResponseEntity<Void> removeSong(@PathVariable Long id, @PathVariable Long songId) {
        playlistService.removeSong(id, songId);
        return ResponseEntity.noContent().build();
    }
}

package com.musicapp.crud.controller;
import com.musicapp.crud.service.SongService;
import com.musicapp.crud.dto.request.*;
import com.musicapp.crud.dto.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {
    private final SongService songService;
    public SongController(SongService songService) { this.songService = songService; }

    @PostMapping("/")
    public ResponseEntity<SongResponse> createSong(@Valid @RequestBody CreateSongRequest req) {
        return ResponseEntity.status(201).body(songService.createSong(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongResponse> getSong(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSong(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SongResponse>> searchSongs(@RequestParam("q") String query) {
        return ResponseEntity.ok(songService.search(query));
    }

    @PatchMapping("/{id}/audio")
    public ResponseEntity<SongResponse> updateAudio(@PathVariable Long id, @Valid @RequestBody UpdateSongAudioRequest req) {
        return ResponseEntity.ok(songService.updateAudio(id, req));
    }
}

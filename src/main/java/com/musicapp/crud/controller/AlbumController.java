package com.musicapp.crud.controller;
import com.musicapp.crud.service.AlbumService;
import com.musicapp.crud.dto.request.CreateAlbumRequest;
import com.musicapp.crud.dto.response.AlbumResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumService albumService;
    public AlbumController(AlbumService albumService) { this.albumService = albumService; }

    @PostMapping("/")
    public ResponseEntity<AlbumResponse> createAlbum(@Valid @RequestBody CreateAlbumRequest req) {
        return ResponseEntity.status(201).body(albumService.createAlbum(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumResponse> getAlbum(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.getAlbum(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<AlbumResponse>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }
}

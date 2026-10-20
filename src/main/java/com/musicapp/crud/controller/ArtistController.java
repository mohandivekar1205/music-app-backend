package com.musicapp.crud.controller;
import com.musicapp.crud.service.*;
import com.musicapp.crud.dto.request.*;
import com.musicapp.crud.dto.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    private final ArtistService artistService;
    private final AlbumService albumService;
    public ArtistController(ArtistService artistService, AlbumService albumService) { 
        this.artistService = artistService; this.albumService = albumService; 
    }

    @PostMapping("/")
    public ResponseEntity<ArtistResponse> createArtist(@Valid @RequestBody CreateArtistRequest req) {
        return ResponseEntity.status(201).body(artistService.createArtist(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponse> getArtist(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getArtist(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<ArtistResponse>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    @PostMapping("/{id}/follow")
    public ResponseEntity<Void> followArtist(@PathVariable Long id, @RequestParam Long userId) {
        artistService.followArtist(userId, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/follow")
    public ResponseEntity<Void> unfollowArtist(@PathVariable Long id, @RequestParam Long userId) {
        artistService.unfollowArtist(userId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/albums")
    public ResponseEntity<List<AlbumResponse>> getArtistAlbums(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.getAlbumsByArtist(id));
    }
}

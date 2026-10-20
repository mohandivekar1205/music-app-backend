package com.musicapp.crud.controller;
import com.musicapp.crud.service.PodcastService;
import com.musicapp.crud.dto.request.*;
import com.musicapp.crud.dto.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/podcasts")
public class PodcastController {
    private final PodcastService podcastService;
    public PodcastController(PodcastService podcastService) { this.podcastService = podcastService; }

    @PostMapping("/")
    public ResponseEntity<PodcastResponse> createPodcast(@Valid @RequestBody CreatePodcastRequest req) {
        return ResponseEntity.status(201).body(podcastService.createPodcast(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PodcastResponse> getPodcast(@PathVariable Long id) {
        return ResponseEntity.ok(podcastService.getPodcast(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<PodcastResponse>> getAllPodcasts() {
        return ResponseEntity.ok(podcastService.getAllPodcasts());
    }

    @PostMapping("/{id}/episodes")
    public ResponseEntity<PodcastEpisodeResponse> addEpisode(@PathVariable Long id, @Valid @RequestBody CreatePodcastEpisodeRequest req) {
        return ResponseEntity.status(201).body(podcastService.addEpisode(id, req));
    }

    @PostMapping("/{id}/subscribe")
    public ResponseEntity<Void> subscribe(@PathVariable Long id, @RequestParam Long userId) {
        podcastService.subscribe(userId, id);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}/subscribe")
    public ResponseEntity<Void> unsubscribe(@PathVariable Long id, @RequestParam Long userId) {
        podcastService.unsubscribe(userId, id);
        return ResponseEntity.noContent().build();
    }
}

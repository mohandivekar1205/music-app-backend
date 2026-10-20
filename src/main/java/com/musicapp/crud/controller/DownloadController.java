package com.musicapp.crud.controller;
import com.musicapp.crud.service.DownloadService;
import com.musicapp.crud.dto.response.DownloadResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/downloads")
public class DownloadController {
    private final DownloadService downloadService;
    public DownloadController(DownloadService downloadService) { this.downloadService = downloadService; }

    @PostMapping("/")
    public ResponseEntity<DownloadResponse> createDownload(@RequestParam Long userId, @RequestParam Long deviceId, @RequestParam String mediaType, @RequestParam Long mediaId) {
        return ResponseEntity.status(201).body(downloadService.createDownload(userId, deviceId, mediaType, mediaId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DownloadResponse>> getUserDownloads(@PathVariable Long userId) {
        return ResponseEntity.ok(downloadService.getUserDownloads(userId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DownloadResponse> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(downloadService.updateStatus(id, status));
    }
}

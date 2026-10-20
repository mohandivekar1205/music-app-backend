package com.musicapp.crud.controller;
import com.musicapp.crud.service.HistoryService;
import com.musicapp.crud.dto.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
    private final HistoryService historyService;
    public HistoryController(HistoryService historyService) { this.historyService = historyService; }

    @PostMapping("/play")
    public ResponseEntity<Void> recordPlay(@RequestParam Long userId, @RequestParam String mediaType, @RequestParam Long mediaId, @RequestParam Integer duration) {
        historyService.recordPlay(userId, mediaType, mediaId, duration);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/search")
    public ResponseEntity<Void> recordSearch(@RequestParam Long userId, @RequestParam String keyword) {
        historyService.recordSearch(userId, keyword);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/play/{userId}")
    public ResponseEntity<List<PlayHistoryResponse>> getPlayHistory(@PathVariable Long userId, Pageable pageable) {
        return ResponseEntity.ok(historyService.getPlayHistory(userId, pageable));
    }

    @GetMapping("/search/{userId}")
    public ResponseEntity<List<SearchHistoryResponse>> getSearchHistory(@PathVariable Long userId, Pageable pageable) {
        return ResponseEntity.ok(historyService.getSearchHistory(userId, pageable));
    }
}

package com.musicapp.crud.service;
import com.musicapp.crud.model.*;
import com.musicapp.crud.model.enums.*;
import com.musicapp.crud.repository.*;
import com.musicapp.crud.dto.response.*;
import com.musicapp.crud.dto.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final PlayHistoryRepository playHistoryRepository;
    private final SearchHistoryRepository searchHistoryRepository;
    private final UserRepository userRepository;

    public void recordPlay(Long userId, String mediaType, Long mediaId, Integer duration) {
        User user = userRepository.findById(userId).orElseThrow();
        PlayHistory ph = PlayHistory.builder()
            .mediaType(MediaType.valueOf(mediaType))
            .mediaId(mediaId)
            .playedAt(Instant.now())
            .playDuration(duration)
            .user(user)
            .build();
        playHistoryRepository.save(ph);
    }

    public void recordSearch(Long userId, String keyword) {
        User user = userRepository.findById(userId).orElseThrow();
        SearchHistory sh = SearchHistory.builder()
            .keyword(keyword)
            .searchedAt(Instant.now())
            .user(user)
            .build();
        searchHistoryRepository.save(sh);
    }

    public List<PlayHistoryResponse> getPlayHistory(Long userId, Pageable pageable) {
        return playHistoryRepository.findByUser_UserIdOrderByPlayedAtDesc(userId, pageable)
            .stream().map(Mapper::toResponse).collect(Collectors.toList());
    }

    public List<SearchHistoryResponse> getSearchHistory(Long userId, Pageable pageable) {
        return searchHistoryRepository.findByUser_UserIdOrderBySearchedAtDesc(userId, pageable)
            .stream().map(Mapper::toResponse).collect(Collectors.toList());
    }
}

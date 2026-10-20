package com.musicapp.crud.service;
import com.musicapp.crud.model.*;
import com.musicapp.crud.repository.*;
import com.musicapp.crud.dto.request.*;
import com.musicapp.crud.dto.response.*;
import com.musicapp.crud.dto.Mapper;
import com.musicapp.crud.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PodcastService {
    private final PodcastRepository podcastRepository;
    private final PodcastEpisodeRepository podcastEpisodeRepository;
    private final PodcastSubscriberRepository podcastSubscriberRepository;
    private final UserRepository userRepository;

    public PodcastResponse createPodcast(CreatePodcastRequest req) {
        Podcast p = Podcast.builder()
            .title(req.title())
            .author(req.author())
            .coverImage(req.coverImage())
            .description(req.description())
            .category(req.category())
            .language(req.language())
            .createdAt(Instant.now())
            .build();
        return Mapper.toResponse(podcastRepository.save(p));
    }

    public PodcastResponse getPodcast(Long id) {
        return podcastRepository.findById(id).map(Mapper::toResponse).orElseThrow(() -> new ResourceNotFoundException("Podcast not found"));
    }

    public List<PodcastResponse> getAllPodcasts() {
        return podcastRepository.findAll().stream().map(Mapper::toResponse).collect(Collectors.toList());
    }

    public PodcastEpisodeResponse addEpisode(Long podcastId, CreatePodcastEpisodeRequest req) {
        Podcast p = podcastRepository.findById(podcastId).orElseThrow(() -> new ResourceNotFoundException("Podcast not found"));
        PodcastEpisode ep = PodcastEpisode.builder()
            .title(req.title())
            .description(req.description())
            .duration(req.duration())
            .audioUrl(req.audioUrl())
            .releaseDate(req.releaseDate())
            .episodeNumber(req.episodeNumber())
            .podcast(p)
            .build();
        return Mapper.toResponse(podcastEpisodeRepository.save(ep));
    }
    
    public void subscribe(Long userId, Long podcastId) {
        PodcastSubscriberId id = new PodcastSubscriberId(userId, podcastId);
        if (podcastSubscriberRepository.existsById(id)) return;
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Podcast podcast = podcastRepository.findById(podcastId).orElseThrow(() -> new ResourceNotFoundException("Podcast not found"));
        podcastSubscriberRepository.save(PodcastSubscriber.builder().id(id).user(user).podcast(podcast).subscribedAt(Instant.now()).build());
    }
    
    public void unsubscribe(Long userId, Long podcastId) {
        podcastSubscriberRepository.deleteById(new PodcastSubscriberId(userId, podcastId));
    }
}

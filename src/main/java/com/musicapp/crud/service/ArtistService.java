package com.musicapp.crud.service;
import com.musicapp.crud.model.*;
import com.musicapp.crud.repository.*;
import com.musicapp.crud.dto.request.*;
import com.musicapp.crud.dto.response.*;
import com.musicapp.crud.dto.Mapper;
import com.musicapp.crud.exception.*;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;
    private final ArtistFollowerRepository artistFollowerRepository;

    public ArtistResponse createArtist(CreateArtistRequest req) {
        Artist artist = Artist.builder()
            .name(req.name())
            .coverImage(req.coverImage())
            .biography(req.biography())
            .verified(req.verified() != null ? req.verified() : false)
            .createdAt(Instant.now())
            .build();
        return Mapper.toResponse(artistRepository.save(artist));
    }

    public ArtistResponse getArtist(Long id) {
        return artistRepository.findById(id)
            .map(Mapper::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
    }

    public List<ArtistResponse> getAllArtists() {
        return artistRepository.findAll().stream().map(Mapper::toResponse).collect(Collectors.toList());
    }

    public void followArtist(Long userId, Long artistId) {
        ArtistFollowerId id = new ArtistFollowerId(artistId, userId);
        if (artistFollowerRepository.existsById(id)) return;
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
        artistFollowerRepository.save(ArtistFollower.builder().id(id).user(user).artist(artist).followedAt(Instant.now()).build());
    }

    public void unfollowArtist(Long userId, Long artistId) {
        ArtistFollowerId id = new ArtistFollowerId(artistId, userId);
        artistFollowerRepository.deleteById(id);
    }

    public long getFollowerCount(Long artistId) {
        return artistFollowerRepository.countById_ArtistId(artistId);
    }
}

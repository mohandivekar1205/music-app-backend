package com.musicapp.crud.service;
import com.musicapp.crud.model.*;
import com.musicapp.crud.model.enums.*;
import com.musicapp.crud.repository.*;
import com.musicapp.crud.dto.request.*;
import com.musicapp.crud.dto.response.*;
import com.musicapp.crud.dto.Mapper;
import com.musicapp.crud.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.Instant;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PlaylistSongRepository playlistSongRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public PlaylistResponse createPlaylist(Long userId, CreatePlaylistRequest req) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Playlist playlist = Playlist.builder()
            .name(req.name())
            .description(req.description())
            .coverImage(req.coverImage())
            .visibility(VisibilityType.valueOf(req.visibility()))
            .createdAt(Instant.now())
            .updatedAt(Instant.now())
            .owner(user)
            .build();
        return Mapper.toResponse(playlistRepository.save(playlist), Collections.emptyList());
    }

    public PlaylistResponse getPlaylist(Long id) {
        Playlist playlist = playlistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Playlist not found"));
        List<PlaylistSong> ps = playlistSongRepository.findByPlaylist_PlaylistIdOrderByPosition(id);
        List<SongResponse> songs = ps.stream()
            .map(p -> Mapper.toResponse(p.getSong(), Collections.singletonList(p.getSong().getAlbum().getArtist())))
            .collect(Collectors.toList());
        return Mapper.toResponse(playlist, songs);
    }

    public List<PlaylistSummary> getUserPlaylists(Long userId) {
        return playlistRepository.findByOwner_UserId(userId).stream().map(Mapper::toSummary).collect(Collectors.toList());
    }

    public PlaylistResponse updatePlaylist(Long id, UpdatePlaylistRequest req) {
        Playlist playlist = playlistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Playlist not found"));
        if (req.name() != null) playlist.setName(req.name());
        if (req.description() != null) playlist.setDescription(req.description());
        if (req.coverImage() != null) playlist.setCoverImage(req.coverImage());
        if (req.visibility() != null) playlist.setVisibility(VisibilityType.valueOf(req.visibility()));
        playlist.setUpdatedAt(Instant.now());
        playlistRepository.save(playlist);
        return getPlaylist(id);
    }

    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    public void addSong(Long playlistId, AddSongToPlaylistRequest req) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new ResourceNotFoundException("Playlist not found"));
        Song song = songRepository.findById(req.songId()).orElseThrow(() -> new ResourceNotFoundException("Song not found"));
        PlaylistSongId id = new PlaylistSongId(playlistId, req.songId());
        PlaylistSong ps = PlaylistSong.builder().id(id).playlist(playlist).song(song)
            .position(req.position() != null ? req.position() : 0).addedAt(Instant.now()).build();
        playlistSongRepository.save(ps);
    }

    public void removeSong(Long playlistId, Long songId) {
        playlistSongRepository.deleteById(new PlaylistSongId(playlistId, songId));
    }
}

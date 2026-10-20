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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public AlbumResponse createAlbum(CreateAlbumRequest req) {
        Artist artist = artistRepository.findById(req.artistId())
            .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
        Album album = Album.builder()
            .title(req.title())
            .coverImage(req.coverImage())
            .releaseDate(req.releaseDate())
            .releaseYear(req.releaseYear())
            .albumType(AlbumType.valueOf(req.albumType()))
            .description(req.description())
            .createdAt(Instant.now())
            .artist(artist)
            .build();
        return Mapper.toResponse(albumRepository.save(album));
    }

    public AlbumResponse getAlbum(Long id) {
        return albumRepository.findById(id)
            .map(Mapper::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Album not found"));
    }

    public List<AlbumResponse> getAllAlbums() {
        return albumRepository.findAll().stream().map(Mapper::toResponse).collect(Collectors.toList());
    }

    public List<AlbumResponse> getAlbumsByArtist(Long artistId) {
        return albumRepository.findByArtist_ArtistId(artistId).stream().map(Mapper::toResponse).collect(Collectors.toList());
    }
}

package com.musicapp.crud.service;
import com.musicapp.crud.model.*;
import com.musicapp.crud.repository.*;
import com.musicapp.crud.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final LikedSongRepository likedSongRepository;
    private final LikedAlbumRepository likedAlbumRepository;
    private final LikedPlaylistRepository likedPlaylistRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final PlaylistRepository playlistRepository;

    public void likeSong(Long userId, Long songId) {
        LikedSongId id = new LikedSongId(userId, songId);
        if (likedSongRepository.existsById(id)) return;
        User user = userRepository.findById(userId).orElseThrow();
        Song song = songRepository.findById(songId).orElseThrow();
        likedSongRepository.save(LikedSong.builder().id(id).user(user).song(song).likedAt(Instant.now()).build());
    }

    public void unlikeSong(Long userId, Long songId) {
        likedSongRepository.deleteById(new LikedSongId(userId, songId));
    }

    public void likeAlbum(Long userId, Long albumId) {
        LikedAlbumId id = new LikedAlbumId(userId, albumId);
        if (likedAlbumRepository.existsById(id)) return;
        User user = userRepository.findById(userId).orElseThrow();
        Album album = albumRepository.findById(albumId).orElseThrow();
        likedAlbumRepository.save(LikedAlbum.builder().id(id).user(user).album(album).likedAt(Instant.now()).build());
    }

    public void unlikeAlbum(Long userId, Long albumId) {
        likedAlbumRepository.deleteById(new LikedAlbumId(userId, albumId));
    }

    public void likePlaylist(Long userId, Long playlistId) {
        LikedPlaylistId id = new LikedPlaylistId(userId, playlistId);
        if (likedPlaylistRepository.existsById(id)) return;
        User user = userRepository.findById(userId).orElseThrow();
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow();
        likedPlaylistRepository.save(LikedPlaylist.builder().id(id).user(user).playlist(playlist).likedAt(Instant.now()).build());
    }

    public void unlikePlaylist(Long userId, Long playlistId) {
        likedPlaylistRepository.deleteById(new LikedPlaylistId(userId, playlistId));
    }

    public Map<String, Object> getUserLibrary(Long userId) {
        Map<String, Object> library = new HashMap<>();
        library.put("likedSongs", likedSongRepository.findById_userId(userId).stream().map(ls -> ls.getSong().getSongId()).collect(Collectors.toList()));
        library.put("likedAlbums", likedAlbumRepository.findById_userId(userId).stream().map(la -> la.getAlbum().getAlbumId()).collect(Collectors.toList()));
        library.put("likedPlaylists", likedPlaylistRepository.findById_userId(userId).stream().map(lp -> lp.getPlaylist().getPlaylistId()).collect(Collectors.toList()));
        return library;
    }
}

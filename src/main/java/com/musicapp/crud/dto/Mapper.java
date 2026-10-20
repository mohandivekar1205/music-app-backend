package com.musicapp.crud.dto;
import com.musicapp.crud.model.*;
import com.musicapp.crud.dto.response.*;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

public class Mapper {
    public static UserResponse toResponse(User user) {
        if (user == null) return null;
        return new UserResponse(
            user.getUserId(), user.getUsername(), user.getEmail(), user.getDisplayName(),
            user.getProfileImage(), user.getBio(), user.getCountry(),
            user.getSubscriptionType() != null ? user.getSubscriptionType().name() : null,
            user.getCreatedAt(), user.getUpdatedAt()
        );
    }
    public static ArtistResponse toResponse(Artist artist) {
        if (artist == null) return null;
        return new ArtistResponse(
            artist.getArtistId(), artist.getName(), artist.getCoverImage(),
            artist.getBiography(), artist.getVerified(), artist.getCreatedAt()
        );
    }
    public static ArtistSummary toSummary(Artist artist) {
        if (artist == null) return null;
        return new ArtistSummary(artist.getArtistId(), artist.getName(), artist.getCoverImage());
    }
    public static AlbumResponse toResponse(Album album) {
        if (album == null) return null;
        return new AlbumResponse(
            album.getAlbumId(), album.getTitle(), album.getCoverImage(),
            album.getReleaseDate(), album.getReleaseYear(),
            album.getAlbumType() != null ? album.getAlbumType().name() : null,
            album.getDescription(), album.getCreatedAt(),
            toSummary(album.getArtist())
        );
    }
    public static AlbumSummary toSummary(Album album) {
        if (album == null) return null;
        return new AlbumSummary(
            album.getAlbumId(), album.getTitle(), album.getCoverImage(),
            album.getAlbumType() != null ? album.getAlbumType().name() : null
        );
    }
    public static SongResponse toResponse(Song song, List<Artist> artists) {
        if (song == null) return null;
        List<ArtistSummary> artistSummaries = artists != null ? 
            artists.stream().map(Mapper::toSummary).collect(Collectors.toList()) : new ArrayList<>();
        return new SongResponse(
            song.getSongId(), song.getTitle(), song.getDuration(), song.getTrackNumber(),
            song.getAudioUrl(), song.getCoverImage(),
            song.getStatus() != null ? song.getStatus().name() : null,
            toSummary(song.getAlbum()), artistSummaries
        );
    }
    public static PlaylistResponse toResponse(Playlist playlist, List<SongResponse> songs) {
        if (playlist == null) return null;
        return new PlaylistResponse(
            playlist.getPlaylistId(), playlist.getName(), playlist.getDescription(),
            playlist.getCoverImage(),
            playlist.getVisibility() != null ? playlist.getVisibility().name() : null,
            playlist.getCreatedAt(), playlist.getUpdatedAt(),
            toResponse(playlist.getOwner()), songs
        );
    }
    public static PlaylistSummary toSummary(Playlist playlist) {
        if (playlist == null) return null;
        return new PlaylistSummary(
            playlist.getPlaylistId(), playlist.getName(), playlist.getCoverImage(),
            playlist.getVisibility() != null ? playlist.getVisibility().name() : null
        );
    }
    public static PodcastResponse toResponse(Podcast podcast) {
        if (podcast == null) return null;
        return new PodcastResponse(
            podcast.getPodcastId(), podcast.getTitle(), podcast.getAuthor(),
            podcast.getCoverImage(), podcast.getDescription(),
            podcast.getCategory(), podcast.getLanguage(), podcast.getCreatedAt()
        );
    }
    public static PodcastEpisodeResponse toResponse(PodcastEpisode episode) {
        if (episode == null) return null;
        return new PodcastEpisodeResponse(
            episode.getEpisodeId(), episode.getTitle(), episode.getDescription(),
            episode.getDuration(), episode.getAudioUrl(), episode.getReleaseDate(),
            episode.getEpisodeNumber(), toResponse(episode.getPodcast())
        );
    }
    public static DeviceResponse toResponse(Device device) {
        if (device == null) return null;
        return new DeviceResponse(
            device.getDeviceId(), device.getDeviceName(),
            device.getPlatform() != null ? device.getPlatform().name() : null,
            device.getLastLogin()
        );
    }
    public static DownloadResponse toResponse(Download download) {
        if (download == null) return null;
        return new DownloadResponse(
            download.getDownloadId(),
            download.getMediaType() != null ? download.getMediaType().name() : null,
            download.getMediaId(), download.getDownloadedAt(),
            download.getStatus() != null ? download.getStatus().name() : null,
            download.getExpiresAt()
        );
    }
    public static PlayHistoryResponse toResponse(PlayHistory ph) {
        if (ph == null) return null;
        return new PlayHistoryResponse(
            ph.getHistoryId(),
            ph.getMediaType() != null ? ph.getMediaType().name() : null,
            ph.getMediaId(), ph.getPlayedAt(), ph.getPlayDuration()
        );
    }
    public static SearchHistoryResponse toResponse(SearchHistory sh) {
        if (sh == null) return null;
        return new SearchHistoryResponse(
            sh.getSearchId(), sh.getKeyword(), sh.getSearchedAt()
        );
    }
}

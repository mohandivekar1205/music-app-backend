package com.musicapp.crud.service;
import com.musicapp.crud.model.*;
import com.musicapp.crud.model.enums.*;
import com.musicapp.crud.repository.*;
import com.musicapp.crud.dto.response.*;
import com.musicapp.crud.dto.Mapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DownloadService {
    private final DownloadRepository downloadRepository;
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    public DownloadResponse createDownload(Long userId, Long deviceId, String mediaType, Long mediaId) {
        User user = userRepository.findById(userId).orElseThrow();
        Device device = deviceRepository.findById(deviceId).orElseThrow();
        Download download = Download.builder()
            .mediaType(MediaType.valueOf(mediaType))
            .mediaId(mediaId)
            .downloadedAt(Instant.now())
            .status(DownloadStatus.PENDING)
            .user(user)
            .device(device)
            .build();
        return Mapper.toResponse(downloadRepository.save(download));
    }

    public List<DownloadResponse> getUserDownloads(Long userId) {
        return downloadRepository.findByUser_UserId(userId).stream()
            .map(Mapper::toResponse).collect(Collectors.toList());
    }

    public DownloadResponse updateStatus(Long id, String status) {
        Download download = downloadRepository.findById(id).orElseThrow();
        download.setStatus(DownloadStatus.valueOf(status));
        return Mapper.toResponse(downloadRepository.save(download));
    }
}

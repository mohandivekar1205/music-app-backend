package com.musicapp.crud.controller;

import com.musicapp.crud.model.Song;
import com.musicapp.crud.model.enums.SongStatus;
import com.musicapp.crud.repository.AlbumRepository;
import com.musicapp.crud.repository.SongRepository;
import com.musicapp.crud.service.StorageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileUploadController.class)
class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StorageService storageService;

    @MockBean
    private SongRepository songRepository;

    @MockBean
    private AlbumRepository albumRepository;

    @Test
    void testUploadAudioFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test-song.mp3",
                "audio/mpeg",
                "dummy audio content".getBytes()
        );

        String fakeUrl = "https://qeqxuimtdjwwnvuyykch.supabase.co/storage/v1/object/public/music-app/songs/test-song.mp3";
        Mockito.when(storageService.uploadFile(any(), eq("songs"))).thenReturn(fakeUrl);

        Song savedSong = Song.builder()
                .songId(101L)
                .title("Test Song")
                .audioUrl(fakeUrl)
                .duration(180)
                .trackNumber(1)
                .status(SongStatus.DRAFT)
                .createdAt(Instant.now())
                .build();

        Mockito.when(songRepository.save(any(Song.class))).thenReturn(savedSong);

        mockMvc.perform(multipart("/api/files/upload")
                        .file(file)
                        .param("title", "Test Song")
                        .param("duration", "180")
                        .param("trackNumber", "1"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.songId").value(101))
                .andExpect(jsonPath("$.title").value("Test Song"))
                .andExpect(jsonPath("$.audioUrl").value(fakeUrl))
                .andExpect(jsonPath("$.uploadedFileUrl").value(fakeUrl));
    }
}

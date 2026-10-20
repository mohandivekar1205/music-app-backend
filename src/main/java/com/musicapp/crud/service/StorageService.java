package com.musicapp.crud.service;

import com.musicapp.crud.exception.InvalidRequestException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@Getter
public class StorageService {

    // Properties imported from application.properties
    private final String supabaseUrl;
    private final String bucket;
    private final String secretKey;
    private final RestClient restClient;

    public StorageService(
            @Value("${supabase.url}") String supabaseUrl,
            @Value("${supabase.storage.bucket}") String bucket,
            @Value("${supabase.secret-key}") String secretKey
    ) {
        this.supabaseUrl = supabaseUrl;
        this.bucket = bucket;
        this.secretKey = secretKey;
        this.restClient = RestClient.create();
    }

    /**
     * Uploads a file to Supabase Storage and returns its public URL.
     *
     * @param file   The multipart file to upload
     * @param folder The folder / subfolder in the bucket (e.g. "songs", "covers")
     * @return The public URL of the uploaded file
     */
    public String uploadFile(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            throw new InvalidRequestException("File cannot be empty");
        }

        if (secretKey == null || secretKey.trim().isEmpty()) {
            throw new InvalidRequestException("Supabase secret key is not configured. Please set 'supabase.secret-key' in your .env or application.properties file.");
        }

        String baseUrl = supabaseUrl.endsWith("/") ? supabaseUrl.substring(0, supabaseUrl.length() - 1) : supabaseUrl;
        if (baseUrl.contains(".storage.supabase.co/storage/v1/s3")) {
            baseUrl = baseUrl.replace(".storage.supabase.co/storage/v1/s3", ".supabase.co");
        } else if (baseUrl.endsWith("/storage/v1/s3")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - "/storage/v1/s3".length());
        }

        String originalFilename = file.getOriginalFilename() != null ? file.getOriginalFilename() : "file";
        String sanitizedName = originalFilename.replaceAll("[^a-zA-Z0-9._-]", "_");
        String uniqueFileName = UUID.randomUUID().toString() + "_" + sanitizedName;

        String folderPrefix = (folder != null && !folder.trim().isEmpty()) ? folder.trim() + "/" : "";
        String objectPath = folderPrefix + uniqueFileName;

        String uploadUrl = String.format("%s/storage/v1/object/%s/%s", baseUrl, bucket, objectPath);

        String contentType = file.getContentType();
        if (contentType == null || contentType.isBlank()) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        try {
            byte[] fileBytes = file.getBytes();

            restClient.post()
                    .uri(uploadUrl)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + secretKey.trim())
                    .header("apikey", secretKey.trim())
                    .header("x-upsert", "true")
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(fileBytes)
                    .retrieve()
                    .toBodilessEntity();

            String publicUrl = String.format("%s/storage/v1/object/public/%s/%s", baseUrl, bucket, objectPath);
            log.info("File uploaded successfully to Supabase Storage: {}", publicUrl);
            return publicUrl;
        } catch (RestClientResponseException ex) {
            log.error("Supabase Storage error ({}): {}", ex.getStatusCode(), ex.getResponseBodyAsString());
            throw new InvalidRequestException("Supabase Storage error (" + ex.getStatusCode() + "): " + ex.getResponseBodyAsString());
        } catch (IOException ex) {
            log.error("Failed to read upload file bytes", ex);
            throw new InvalidRequestException("Failed to read file contents: " + ex.getMessage());
        } catch (Exception ex) {
            log.error("Unexpected error during file upload", ex);
            throw new InvalidRequestException("File upload failed: " + ex.getMessage());
        }
    }

    /**
     * Convenience method to upload a file into default "songs" directory.
     */
    public String uploadFile(MultipartFile file) {
        return uploadFile(file, "songs");
    }

    /**
     * Resolves the public URL for an existing object path.
     */
    public String getPublicUrl(String objectPath) {
        String baseUrl = supabaseUrl.endsWith("/") ? supabaseUrl.substring(0, supabaseUrl.length() - 1) : supabaseUrl;
        if (baseUrl.contains(".storage.supabase.co/storage/v1/s3")) {
            baseUrl = baseUrl.replace(".storage.supabase.co/storage/v1/s3", ".supabase.co");
        } else if (baseUrl.endsWith("/storage/v1/s3")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - "/storage/v1/s3".length());
        }
        return String.format("%s/storage/v1/object/public/%s/%s", baseUrl, bucket, objectPath);
    }
}

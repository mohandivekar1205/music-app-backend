package com.musicapp.crud.repository;
import com.musicapp.crud.model.Download;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface DownloadRepository extends JpaRepository<Download, Long> {
    List<Download> findByUser_UserId(Long userId);
}

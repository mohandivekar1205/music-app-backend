package com.musicapp.crud.repository;
import com.musicapp.crud.model.PlayHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface PlayHistoryRepository extends JpaRepository<PlayHistory, Long> {
    List<PlayHistory> findByUser_UserIdOrderByPlayedAtDesc(Long userId, Pageable pageable);
}

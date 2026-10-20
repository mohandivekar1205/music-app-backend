package com.musicapp.crud.repository;
import com.musicapp.crud.model.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
    List<SearchHistory> findByUser_UserIdOrderBySearchedAtDesc(Long userId, Pageable pageable);
}

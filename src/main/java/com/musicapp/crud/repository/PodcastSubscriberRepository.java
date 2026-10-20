package com.musicapp.crud.repository;
import com.musicapp.crud.model.PodcastSubscriber;
import com.musicapp.crud.model.PodcastSubscriberId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PodcastSubscriberRepository extends JpaRepository<PodcastSubscriber, PodcastSubscriberId> {
    List<PodcastSubscriber> findById_userId(Long userId);
    boolean existsById(PodcastSubscriberId id);
}

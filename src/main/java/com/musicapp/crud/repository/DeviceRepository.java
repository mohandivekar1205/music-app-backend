package com.musicapp.crud.repository;
import com.musicapp.crud.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByUser_UserId(Long userId);
}

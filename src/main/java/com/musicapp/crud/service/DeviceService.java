package com.musicapp.crud.service;
import com.musicapp.crud.model.*;
import com.musicapp.crud.model.enums.*;
import com.musicapp.crud.repository.*;
import com.musicapp.crud.dto.request.*;
import com.musicapp.crud.dto.response.*;
import com.musicapp.crud.dto.Mapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    public DeviceResponse registerDevice(Long userId, RegisterDeviceRequest req) {
        User user = userRepository.findById(userId).orElseThrow();
        Device device = Device.builder()
            .deviceName(req.deviceName())
            .platform(DevicePlatform.valueOf(req.platform()))
            .deviceToken(req.deviceToken())
            .lastLogin(Instant.now())
            .user(user)
            .build();
        return Mapper.toResponse(deviceRepository.save(device));
    }

    public List<DeviceResponse> getUserDevices(Long userId) {
        return deviceRepository.findByUser_UserId(userId).stream()
            .map(Mapper::toResponse).collect(Collectors.toList());
    }

    public void removeDevice(Long id) {
        deviceRepository.deleteById(id);
    }
}

package com.musicapp.crud.controller;
import com.musicapp.crud.service.DeviceService;
import com.musicapp.crud.dto.request.RegisterDeviceRequest;
import com.musicapp.crud.dto.response.DeviceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    private final DeviceService deviceService;
    public DeviceController(DeviceService deviceService) { this.deviceService = deviceService; }

    @PostMapping("/")
    public ResponseEntity<DeviceResponse> registerDevice(@RequestParam Long userId, @Valid @RequestBody RegisterDeviceRequest req) {
        return ResponseEntity.status(201).body(deviceService.registerDevice(userId, req));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DeviceResponse>> getUserDevices(@PathVariable Long userId) {
        return ResponseEntity.ok(deviceService.getUserDevices(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeDevice(@PathVariable Long id) {
        deviceService.removeDevice(id);
        return ResponseEntity.noContent().build();
    }
}

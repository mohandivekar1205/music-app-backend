package com.musicapp.crud.model;
import com.musicapp.crud.model.enums.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Table(name = "devices", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Device {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;
    private String deviceName;
    @Enumerated(EnumType.STRING) private DevicePlatform platform;
    private String deviceToken;
    private Instant lastLogin;
    @ManyToOne @JoinColumn(name = "user_id")
    private User user;
}

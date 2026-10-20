package com.musicapp.crud.model;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "user_settings", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserSettings {
    @Id
    private Long userId;
    @OneToOne @MapsId
    @JoinColumn(name = "user_id")
    private User user;
    private String theme;
    private String language;
    private String downloadQuality;
    private Boolean autoplay;
    private Boolean notifications;
    private Boolean explicitContent;
}

package com.musicapp.crud.model;
import com.musicapp.crud.model.enums.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Table(name = "users", schema = "music_app")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String email;
    private String passwordHash;
    private String displayName;
    private String profileImage;
    private String bio;
    private String country;
    @Enumerated(EnumType.STRING) private SubscriptionType subscriptionType;
    private Instant createdAt;
    private Instant updatedAt;
}

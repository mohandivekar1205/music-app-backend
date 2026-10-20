package com.musicapp.crud.service;
import com.musicapp.crud.model.*;
import com.musicapp.crud.model.enums.*;
import com.musicapp.crud.repository.*;
import com.musicapp.crud.dto.request.*;
import com.musicapp.crud.dto.response.*;
import com.musicapp.crud.dto.Mapper;
import com.musicapp.crud.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserSettingsRepository userSettingsRepository;

    public UserResponse createUser(CreateUserRequest req) {
        User user = User.builder()
            .username(req.username())
            .email(req.email())
            .passwordHash(req.passwordHash())
            .displayName(req.displayName())
            .profileImage(req.profileImage())
            .bio(req.bio())
            .country(req.country())
            .subscriptionType(req.subscriptionType() != null ? SubscriptionType.valueOf(req.subscriptionType()) : SubscriptionType.FREE)
            .createdAt(Instant.now())
            .updatedAt(Instant.now())
            .build();
        user = userRepository.save(user);

        UserSettings settings = UserSettings.builder()
            .user(user)
            .theme("light")
            .language("en")
            .downloadQuality("high")
            .autoplay(true)
            .notifications(true)
            .explicitContent(false)
            .build();
        userSettingsRepository.save(settings);

        return Mapper.toResponse(user);
    }

    public UserResponse getUser(Long id) {
        return userRepository.findById(id)
            .map(Mapper::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserResponse updateUser(Long id, UpdateUserRequest req) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (req.username() != null) user.setUsername(req.username());
        if (req.email() != null) user.setEmail(req.email());
        if (req.displayName() != null) user.setDisplayName(req.displayName());
        if (req.profileImage() != null) user.setProfileImage(req.profileImage());
        if (req.bio() != null) user.setBio(req.bio());
        if (req.country() != null) user.setCountry(req.country());
        if (req.subscriptionType() != null) user.setSubscriptionType(SubscriptionType.valueOf(req.subscriptionType()));
        user.setUpdatedAt(Instant.now());
        return Mapper.toResponse(userRepository.save(user));
    }
}

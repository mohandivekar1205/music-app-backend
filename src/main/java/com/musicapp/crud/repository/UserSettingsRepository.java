package com.musicapp.crud.repository;
import com.musicapp.crud.model.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {}

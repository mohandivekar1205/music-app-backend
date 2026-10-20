package com.musicapp.crud.repository;
import com.musicapp.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {}

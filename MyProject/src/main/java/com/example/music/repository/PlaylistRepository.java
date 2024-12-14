package com.example.music.repository;

import com.example.music.entity.Playlist;
import com.example.music.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByUser(User user); // User 객체 기반 검색
}

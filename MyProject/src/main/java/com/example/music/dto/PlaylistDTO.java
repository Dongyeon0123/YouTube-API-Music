package com.example.music.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PlaylistDTO {

    private Long id;
    private String name;
    private Long userId;
    private LocalDateTime createdAt;
    private List<YouTubePlaylistItemDTO> items;

    // 기본 생성자
    public PlaylistDTO() {}

    // 모든 필드를 포함하는 생성자
    public PlaylistDTO(Long id, String name, Long userId, LocalDateTime createdAt, List<YouTubePlaylistItemDTO> items) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.createdAt = createdAt;
        this.items = items;
    }

    // 필요한 필드만 포함하는 생성자 추가 (에러 수정)
    public PlaylistDTO(Long id, String name, Long userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<YouTubePlaylistItemDTO> getItems() {
        return items;
    }

    public void setItems(List<YouTubePlaylistItemDTO> items) {
        this.items = items;
    }
}

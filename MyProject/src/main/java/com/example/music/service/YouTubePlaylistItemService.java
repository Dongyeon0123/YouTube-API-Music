package com.example.music.service;

import com.example.music.dto.YouTubePlaylistItemDTO;

import java.util.List;

public interface YouTubePlaylistItemService {
    List<YouTubePlaylistItemDTO> searchItems(String query);

    // 새로운 메서드 추가
    void removeSong(Long playlistId, String songId);
}

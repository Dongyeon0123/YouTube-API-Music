package com.example.music.service;

import com.example.music.dto.YouTubePlaylistItemDTO;
import com.example.music.repository.YouTubePlaylistItemRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class YouTubePlaylistItemServiceImpl implements YouTubePlaylistItemService {

	@Autowired
    private YouTubePlaylistItemRepository youTubePlaylistItemRepository;

    @Autowired
    private YouTubeApiClient youTubeApiClient;

    @Override
    public List<YouTubePlaylistItemDTO> searchItems(String query) {
        return youTubeApiClient.searchItems(query);
    }

    @Override
    @Transactional
    public void removeSong(Long playlistId, String youTubeVideoId) {
        youTubePlaylistItemRepository.deleteByPlaylistIdAndYouTubeVideoId(playlistId, youTubeVideoId);
    }
}

package com.example.music.service;

import com.example.music.dto.PlaylistDTO;
import com.example.music.dto.YouTubePlaylistItemDTO;

import java.util.List;

public interface PlaylistService {
    List<PlaylistDTO> getAllPlaylistsForUser(Long userId);

    PlaylistDTO createPlaylist(String name, Long userId);

    void deletePlaylist(Long playlistId, Long userId);

    void addSongToPlaylist(Long playlistId, YouTubePlaylistItemDTO song);

    void removeSongFromPlaylist(Long playlistId, Long songId);

    List<YouTubePlaylistItemDTO> getSongsInPlaylist(Long playlistId);
}

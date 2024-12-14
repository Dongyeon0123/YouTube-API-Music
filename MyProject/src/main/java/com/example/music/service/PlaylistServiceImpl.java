package com.example.music.service;

import com.example.music.dto.PlaylistDTO;
import com.example.music.dto.YouTubePlaylistItemDTO;
import com.example.music.entity.Playlist;
import com.example.music.entity.User;
import com.example.music.entity.YouTubePlaylistItem;
import com.example.music.repository.PlaylistRepository;
import com.example.music.repository.UserRepository;
import com.example.music.repository.YouTubePlaylistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private YouTubePlaylistItemRepository itemRepository;

    @Override
    public List<PlaylistDTO> getAllPlaylistsForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return playlistRepository.findByUser(user).stream()
                .map(playlist -> new PlaylistDTO(
                        playlist.getId(),
                        playlist.getName(),
                        user.getId() // User의 ID 반환
                ))
                .collect(Collectors.toList());
    }

    @Override
    public PlaylistDTO createPlaylist(String name, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setUser(user);

        Playlist savedPlaylist = playlistRepository.save(playlist);
        return new PlaylistDTO(savedPlaylist.getId(), savedPlaylist.getName(), user.getId());
    }


    @Override
    public void deletePlaylist(Long playlistId, Long userId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("플레이리스트를 찾을 수 없습니다."));

        if (!playlist.getUser().getId().equals(userId)) {
            throw new SecurityException("삭제 권한이 없습니다.");
        }

        playlistRepository.delete(playlist);
    }

    @Override
    public void addSongToPlaylist(Long playlistId, YouTubePlaylistItemDTO song) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("플레이리스트를 찾을 수 없습니다."));

        YouTubePlaylistItem item = new YouTubePlaylistItem();
        item.setTitle(song.getTitle());
        item.setDescription(song.getDescription());
        item.setYouTubeVideoId(song.getId());
        item.setPlaylist(playlist);

        itemRepository.save(item);
    }

    @Override
    public void removeSongFromPlaylist(Long playlistId, Long songId) {
        YouTubePlaylistItem item = itemRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("노래를 찾을 수 없습니다."));

        if (!item.getPlaylist().getId().equals(playlistId)) {
            throw new SecurityException("삭제 권한이 없습니다.");
        }

        itemRepository.delete(item);
    }

    @Override
    public List<YouTubePlaylistItemDTO> getSongsInPlaylist(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("플레이리스트를 찾을 수 없습니다."));

        return playlist.getSongs().stream()
                .map(item -> new YouTubePlaylistItemDTO(
                        item.getYouTubeVideoId(),
                        item.getTitle(),
                        item.getDescription(),
                        null, // 채널 ID는 생략
                        item.getPlaylist().getId().toString()
                ))
                .collect(Collectors.toList());
    }
}

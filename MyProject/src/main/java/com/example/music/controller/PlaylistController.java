package com.example.music.controller;

import com.example.music.dto.PlaylistCreationRequest;
import com.example.music.dto.PlaylistDTO;
import com.example.music.dto.YouTubePlaylistItemDTO;
import com.example.music.service.CustomUserDetails;
import com.example.music.service.PlaylistService;
import com.example.music.service.YouTubePlaylistItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;
    
    @Autowired
    private YouTubePlaylistItemService youTubePlaylistItemService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody PlaylistCreationRequest request) {
        Long userId = getAuthenticatedUserId(); // 인증된 사용자 ID 가져오기
        PlaylistDTO playlist = playlistService.createPlaylist(request.getName(), userId);
        return ResponseEntity.ok(playlist);
    }

    private Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }

    // 주석 처리된 기존 메서드 삭제 및 이름 지정된 매개변수 사용
    @DeleteMapping("/{playlistId}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable(name = "playlistId") Long playlistId) {
        Long userId = getAuthenticatedUserId();
        playlistService.deletePlaylist(playlistId, userId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<PlaylistDTO>> getAllPlaylistsForUser() {
        Long userId = getAuthenticatedUserId(); // 인증된 사용자 ID 가져오기
        List<PlaylistDTO> playlists = playlistService.getAllPlaylistsForUser(userId);
        return ResponseEntity.ok(playlists);
    }

    @PostMapping("/{playlistId}/songs")
    public ResponseEntity<Void> addSongToPlaylist(
            @PathVariable("playlistId") Long playlistId,
            @RequestBody YouTubePlaylistItemDTO songDTO) {
        System.out.println("Received playlistId: " + playlistId);
        System.out.println("Received songDTO: " + songDTO); // songDTO.toString()이 필요할 수 있음
        playlistService.addSongToPlaylist(playlistId, songDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{playlistId}/songs/{youTubeVideoId}")
    public ResponseEntity<Void> removeSongFromPlaylist(
            @PathVariable("playlistId") Long playlistId,
            @PathVariable("youTubeVideoId") String youTubeVideoId) {
        System.out.println("playlistId: " + playlistId);
        System.out.println("youTubeVideoId: " + youTubeVideoId);
        youTubePlaylistItemService.removeSong(playlistId, youTubeVideoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{playlistId}/songs")
    public ResponseEntity<List<YouTubePlaylistItemDTO>> getSongsInPlaylist(
            @PathVariable("playlistId") Long playlistId) {
        System.out.println("Received playlistId: " + playlistId);
        List<YouTubePlaylistItemDTO> songs = playlistService.getSongsInPlaylist(playlistId);
        return ResponseEntity.ok(songs);
    }

    
    
}

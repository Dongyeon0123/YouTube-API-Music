package com.example.music.controller;

import com.example.music.dto.YouTubePlaylistItemDTO;
import com.example.music.service.YouTubePlaylistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/youtube-playlist-items")
public class YouTubePlaylistItemController {

    @Autowired
    private YouTubePlaylistItemService youtubePlaylistItemService;

    /**
     * 검색 결과를 가져옵니다.
     * @param search 검색어 (선택사항)
     * @return 검색된 항목 목록
     */
    @GetMapping
    public ResponseEntity<List<YouTubePlaylistItemDTO>> searchItems(
            @RequestParam(name = "search", required = false) String search) {
        if (search == null || search.isEmpty()) {
            return ResponseEntity.badRequest().body(List.of()); // 검색어가 없으면 Bad Request 응답
        }
        List<YouTubePlaylistItemDTO> items = youtubePlaylistItemService.searchItems(search);
        return ResponseEntity.ok(items);
    }
}

package com.example.music.service;

import com.example.music.dto.YouTubePlaylistItemDTO;
import com.example.music.dto.youtube.YouTubePlaylistResponse;
import com.example.music.dto.youtube.YouTubeSnippet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class YouTubeApiClient {

    @Value("${youtube.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * YouTube 검색 결과를 가져옵니다.
     */
    public List<YouTubePlaylistItemDTO> searchItems(String query) {
        String url = String.format(
            "https://www.googleapis.com/youtube/v3/search?part=snippet&q=%s&type=video&key=%s",
            query, apiKey
        );

        YouTubePlaylistResponse response = restTemplate.getForObject(url, YouTubePlaylistResponse.class);
        if (response != null && response.getItems() != null) {
            return response.getItems().stream()
                    .map(item -> {
                        YouTubeSnippet snippet = item.getSnippet();
                        String videoId = item.getId().getVideoId();  // videoId를 올바르게 추출
                        return new YouTubePlaylistItemDTO(
                            videoId,  // 올바른 videoId 사용
                            snippet.getTitle(),
                            snippet.getDescription(),
                            snippet.getChannelTitle(),
                            null  // 플레이리스트 ID는 null로 설정
                        );
                    })
                    .collect(Collectors.toList());
        }
        return List.of();  // 응답이 없을 경우 빈 리스트 반환
    }






    private YouTubePlaylistItemDTO convertSnippetToDTO(YouTubeSnippet snippet) {
        if (snippet == null || snippet.getResourceId() == null) {
            // null 처리: 리소스가 없으면 null 값을 반환하거나, 기본값을 설정
            return null;  // 기본적으로 null 반환
        }

        return new YouTubePlaylistItemDTO(
                snippet.getResourceId().getVideoId(), // null 체크 후 접근
                snippet.getTitle(),
                snippet.getDescription(),
                snippet.getChannelTitle(), // Snippet에 포함된 채널명
                null // 플레이리스트 ID는 Snippet에 포함되지 않음
        );
    }



}

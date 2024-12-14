package com.example.music.dto.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubePlaylistResponse {
    private List<YouTubePlaylistItem> items;

    public List<YouTubePlaylistItem> getItems() {
        return items;
    }

    public void setItems(List<YouTubePlaylistItem> items) {
        this.items = items;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class YouTubePlaylistItem {
        private YouTubeSnippet snippet;
        private YouTubeResourceId id;  // 추가된 videoId 필드

        public YouTubeSnippet getSnippet() {
            return snippet;
        }

        public void setSnippet(YouTubeSnippet snippet) {
            this.snippet = snippet;
        }

        public YouTubeResourceId getId() {
            return id;
        }

        public void setId(YouTubeResourceId id) {
            this.id = id;
        }
    }
}

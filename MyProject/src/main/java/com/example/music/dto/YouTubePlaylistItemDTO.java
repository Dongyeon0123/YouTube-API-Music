package com.example.music.dto;

public class YouTubePlaylistItemDTO {
    private String id;
    private String title;
    private String description;
    private String channelId;
    private String playlistId;
    
	 // 기본 생성자
	    public YouTubePlaylistItemDTO() {
	    }

    public YouTubePlaylistItemDTO(String id, String title, String description, String channelId, String playlistId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.channelId = channelId;
        this.playlistId = playlistId;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}

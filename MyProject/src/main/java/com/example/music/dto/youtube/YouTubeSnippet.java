package com.example.music.dto.youtube;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YouTubeSnippet {
    private String title;
    private String description;
    private String channelTitle;

    @JsonProperty("resourceId")
    private ResourceId resourceId;

    // Getter and Setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for channelTitle
    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    // Getter and Setter for resourceId
    public ResourceId getResourceId() {
        return resourceId;
    }

    public void setResourceId(ResourceId resourceId) {
        this.resourceId = resourceId;
    }

    // 내부 ResourceId 클래스 정의
    public static class ResourceId {
        private String videoId;

        // Getter and Setter for videoId
        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }
    }
}

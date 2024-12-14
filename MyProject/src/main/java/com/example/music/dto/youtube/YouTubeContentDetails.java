package com.example.music.dto.youtube;

public class YouTubeContentDetails {
    private String duration; // ISO 8601 format, e.g., "PT5M33S"
    private String dimension;
    private String definition;

    // Getters and Setters
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}

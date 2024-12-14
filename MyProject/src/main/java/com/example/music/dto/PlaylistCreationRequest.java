package com.example.music.dto;

import jakarta.validation.constraints.NotBlank;

public class PlaylistCreationRequest {

    @NotBlank(message = "Playlist name is required.")
    private String name;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

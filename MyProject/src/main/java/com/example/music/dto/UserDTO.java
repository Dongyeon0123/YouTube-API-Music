package com.example.music.dto;

import java.util.List;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String password; // 비밀번호 필드 추가
    private List<PlaylistDTO> playlists;

    // Constructors
    public UserDTO() {}

    public UserDTO(Long id, String username, String email, String phoneNumber, String password, List<PlaylistDTO> playlists) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.playlists = playlists;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() { // 비밀번호 Getter 추가
        return password;
    }

    public void setPassword(String password) { // 비밀번호 Setter 추가
        this.password = password;
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }
}

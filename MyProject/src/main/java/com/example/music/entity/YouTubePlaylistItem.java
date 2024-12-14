package com.example.music.entity;

import com.example.music.dto.youtube.YouTubeSnippet;

import jakarta.persistence.*;

@Entity
@Table(name = "youtube_playlist_items")
public class YouTubePlaylistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = true, foreignKey = @ForeignKey(name = "fk_playlist"))
    private Playlist playlist;  // 플레이리스트 ID는 nullable로 변경 (외부 API와의 통합을 고려)

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "youtube_video_id", nullable = false)
    private String youTubeVideoId;

    @Transient
    private YouTubeSnippet snippet;

    // Getters and Setters
    public YouTubeSnippet getSnippet() {
        return snippet;
    }

    public void setSnippet(YouTubeSnippet snippet) {
        this.snippet = snippet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYouTubeVideoId() {
        return youTubeVideoId;
    }

    public void setYouTubeVideoId(String youTubeVideoId) {
        this.youTubeVideoId = youTubeVideoId;
    }
}

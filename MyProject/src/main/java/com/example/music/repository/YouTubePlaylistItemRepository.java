package com.example.music.repository;

import com.example.music.entity.YouTubePlaylistItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface YouTubePlaylistItemRepository extends JpaRepository<YouTubePlaylistItem, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM YouTubePlaylistItem y WHERE y.playlist.id = :playlistId AND y.youTubeVideoId = :youTubeVideoId")
    void deleteByPlaylistIdAndYouTubeVideoId(@Param("playlistId") Long playlistId, @Param("youTubeVideoId") String youTubeVideoId);
}

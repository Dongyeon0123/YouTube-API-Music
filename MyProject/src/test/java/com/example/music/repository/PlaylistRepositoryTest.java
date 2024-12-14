package com.example.music.repository;

import com.example.music.entity.Playlist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test") // `application-test.properties`를 활성화
public class PlaylistRepositoryTest {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Test
    void testSaveAndFindPlaylist() {
        // Given
        Playlist playlist = new Playlist();
        playlist.setName("Test Playlist");
        playlist = playlistRepository.save(playlist);

        // When
        Optional<Playlist> foundPlaylist = playlistRepository.findById(playlist.getId());

        // Then
        assertThat(foundPlaylist).isPresent();
        assertThat(foundPlaylist.get().getName()).isEqualTo("Test Playlist");
    }

    @Test
    void testDeletePlaylist() {
        // Given
        Playlist playlist = new Playlist();
        playlist.setName("Temporary Playlist");
        playlist = playlistRepository.save(playlist);

        // When
        playlistRepository.deleteById(playlist.getId());

        // Then
        assertThat(playlistRepository.findById(playlist.getId())).isNotPresent();
    }
}

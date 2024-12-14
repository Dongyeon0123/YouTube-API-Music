package com.example.music.controller;

import com.example.music.entity.Playlist;
import com.example.music.repository.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PlaylistControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlaylistRepository playlistRepository;

    @BeforeEach
    void setUp() {
        playlistRepository.deleteAll();
        Playlist playlist = new Playlist();
        playlist.setName("Integration Test Playlist");
        playlistRepository.save(playlist);
    }

    @Test
    void testGetPlaylists() throws Exception {
        mockMvc.perform(get("/playlists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Integration Test Playlist"));
    }
}

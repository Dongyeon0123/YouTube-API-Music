package com.example.music.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class YouTubeAudioController {

    @GetMapping("/get-audio")
    public ResponseEntity<InputStreamResource> getAudio(@RequestParam("videoId") String videoId) {
        System.out.println("Received videoId: " + videoId);

        String audioUrl = getAudioUrlFromYouTube(videoId);
        if (audioUrl != null) {
            try {
                // URL에서 오디오 스트림 가져오기
                URL url = new URL(audioUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStream audioStream = connection.getInputStream();

                // ResponseEntity로 스트림 반환
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Disposition", "inline; filename=\"audio.webm\"");
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(connection.getContentLengthLong())
                        .body(new InputStreamResource(audioStream));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(500).build();
            }
        } else {
            return ResponseEntity.status(500).body(null);
        }
    }

    private String getAudioUrlFromYouTube(String videoId) {
        String videoUrl = "https://www.youtube.com/watch?v=" + videoId;
        try {
            Process process = new ProcessBuilder("/opt/homebrew/bin/yt-dlp", "--extract-audio", "--audio-format", "mp3", "--get-url", videoUrl)
                    .redirectErrorStream(true)
                    .start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String audioUrl = reader.readLine();
            process.waitFor();
            return audioUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

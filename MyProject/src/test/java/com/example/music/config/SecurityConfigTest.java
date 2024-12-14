package com.example.music.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class SecurityConfigTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    void testPasswordEncoder() {
        // Given
        String rawPassword = "testPassword123";

        // When
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Then
        assertThat(passwordEncoder.matches(rawPassword, encodedPassword)).isTrue();
    }

    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    void testAuthenticationSuccess() {
        // Given
        Authentication authentication = authenticationManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken("testUser", "testPassword")
        );

        // Then
        assertThat(authentication.isAuthenticated()).isTrue();
    }

    @Test
    void testAuthenticationFailure() {
        // When
        AuthenticationException exception = assertThrows(AuthenticationException.class, () -> {
            authenticationManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken("invalidUser", "wrongPassword")
            );
        });

        // Then
        assertThat(exception).isNotNull();
    }
}

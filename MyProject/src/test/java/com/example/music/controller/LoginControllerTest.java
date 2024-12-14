package com.example.music.controller;

import com.example.music.service.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginController = new LoginController(authenticationManager);
    }

    @Test
    void testShowLoginForm() {
        String viewName = loginController.showLoginForm();
        assertEquals("login.html", viewName);
    }

    @Test
    void testLoginSuccess() {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken("user", "password");

        when(authenticationManager.authenticate(token)).thenReturn(token);

        Model mockModel = mock(Model.class);
        String result = loginController.login("user", "password", mockModel);

        assertEquals("redirect:/index.html", result);
    }

    @Test
    void testLoginFailure() {
        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Invalid credentials"));

        Model mockModel = mock(Model.class);
        String result = loginController.login("user", "wrongPassword", mockModel);

        assertEquals("login.html", result);
        verify(mockModel).addAttribute(eq("error"), anyString());
    }
}

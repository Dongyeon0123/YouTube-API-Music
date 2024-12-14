package com.example.music.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @GetMapping("/auth/status")
    @ResponseBody
    public Map<String, Object> checkAuthStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> response = new HashMap<>();
        response.put("authenticated", authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal()));
        return response;
    }
}

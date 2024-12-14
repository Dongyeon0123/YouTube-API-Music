package com.example.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // 로그인 페이지 표시
    @GetMapping("/login")
    public String showLoginForm() {
        return "login.html";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, Model model) {
        try {
            // 사용자 아이디와 비밀번호로 인증 토큰 생성
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 인증 성공 시 메인 페이지로 리다이렉션
            return "redirect:/index.html";
        } catch (Exception e) {
            // 로그인 실패 시
            System.out.println("로그인 실패! 아이디와 비밀번호를 확인하세요.");
            model.addAttribute("error", "로그인 실패! 아이디와 비밀번호를 확인하세요.");
            return "login.html";  // 실패 시 로그인 페이지로 다시 돌아감
        }
    }
}

package com.example.music;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

	@GetMapping("/index")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView("index"); // index.html을 렌더링
        boolean isLoggedIn = false;

        // 로그인 상태에 따라 isLoggedIn 값을 설정
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
            SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
            !(SecurityContextHolder.getContext().getAuthentication() instanceof org.springframework.security.authentication.AnonymousAuthenticationToken)) {
            isLoggedIn = true;
        }

        // 로그인 상태를 모델에 추가
        modelAndView.addObject("isLoggedIn", isLoggedIn);

        return modelAndView;
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 세션에서 로그인 상태 제거
        request.getSession().invalidate();
        return "redirect:/login";  // 로그아웃 후 로그인 페이지로 리디렉션
    }
    
    @GetMapping("/songList")
    public String songList() {
        return "songList"; // songList.html 템플릿 반환
    }

    @GetMapping("/playlistDetails")
    public String playlistDetails() {
        return "playlistDetails"; // playlistDetails.html 템플릿 반환
    }
    
    @GetMapping("/register")
    public String register() {
        return "register"; // songList.html 템플릿 반환
    }
}


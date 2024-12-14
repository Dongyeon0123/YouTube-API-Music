package com.example.music.controller;

import com.example.music.dto.UserDTO;
import com.example.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.music.service.CustomUserDetails;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    // 사용자 등록 폼을 제출하고 결과 페이지를 반환
    @PostMapping("/signup")
    public ModelAndView registerUser(@ModelAttribute UserDTO userDTO) {
        System.out.println("Received UserDTO: " + userDTO);
        userService.createUser(userDTO);
        return new ModelAndView("redirect:/login");
    }
    
    @GetMapping("/user/details")
    public ModelAndView getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        ModelAndView modelAndView = new ModelAndView("playlist_management");
        modelAndView.addObject("userId", userId);
        return modelAndView;
    }
    
    @GetMapping("/user-id")
    public UserIdResponse getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return new UserIdResponse(userDetails.getId());
        }
        throw new RuntimeException("User not authenticated");
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    static class UserIdResponse {
        private Long userId;

        public UserIdResponse(Long userId) {
            this.userId = userId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }
}

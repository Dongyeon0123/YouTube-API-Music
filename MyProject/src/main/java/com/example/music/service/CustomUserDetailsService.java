package com.example.music.service;

import com.example.music.entity.Playlist;
import com.example.music.entity.User;
import com.example.music.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new CustomUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getPlaylists())
        );
    }

    private Set<GrantedAuthority> mapRolesToAuthorities(Set<Playlist> playlists) {
        // Example: Add any specific authority logic here if needed.
        // Currently adding a dummy ROLE_USER authority
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }
}

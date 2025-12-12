package com.example.demo;

import com.example.demo.entity.AppUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.CustomUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

@SpringBootTest
class CustomUserDetailsServiceTest {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    void loadUserByUsername_UserExists_ReturnsUserDetails() {
        AppUser user = new AppUser();
        user.setUsername("ram");
        user.setPassword("$2a$10$SnyiVmlTTyt1qnvVA5xFh.Sps8g8YMRwBqKRlBjN23ApezRbwEuZi");
        user.setRole("USER");

        Mockito.when(userRepository.findByUsername("ram")).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername("ram");

        Assertions.assertEquals("ram", userDetails.getUsername());
        Assertions.assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void loadUserByUsername_UserNotFound_ThrowsException() {
        Mockito.when(userRepository.findByUsername("nonexist")).thenReturn(Optional.empty());
        Assertions.assertThrows(UsernameNotFoundException.class, () ->
                userDetailsService.loadUserByUsername("nonexist"));
    }
}

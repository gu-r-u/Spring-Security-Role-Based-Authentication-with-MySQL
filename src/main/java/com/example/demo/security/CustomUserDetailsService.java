package com.example.demo.security;

import com.example.demo.entity.AppUser;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("User found: " + user.getUsername() + " | Role: " + user.getRole());

        // Example: verify the password "admin" or "password"
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matchesAdmin = encoder.matches("admin", user.getPassword());
        boolean matchesPassword = encoder.matches("password", user.getPassword());

        System.out.println("Matches 'admin'? " + matchesAdmin);
        System.out.println("Matches 'password'? " + matchesPassword);

        return new CustomUserDetails(user);
    }

}

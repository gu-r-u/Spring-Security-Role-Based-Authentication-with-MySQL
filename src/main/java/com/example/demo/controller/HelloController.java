package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a PUBLIC endpoint";
    }

    @GetMapping("/hello")
    public String userEndpoint(Authentication authentication) {
        return "Hello USER: " + authentication.getName();
    }

    @GetMapping("/admin")
    public String adminEndpoint(Authentication authentication) {
        return "Hello ADMIN: " + authentication.getName();
    }
}

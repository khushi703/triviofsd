package com.example.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserDetailsController {

    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            String email = userDetails.getUsername(); // Get email from JWT
            String username = email.split("@")[0]; // Extract username
            return Map.of(
                    "username", username,
                    "email", email,
                    "roles", userDetails.getAuthorities().stream().map(Object::toString).toList()
            );
        }
        return Map.of("message", "Guest");
    }
}

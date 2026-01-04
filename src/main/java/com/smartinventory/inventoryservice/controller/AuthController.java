package com.smartinventory.inventoryservice.controller;

import com.smartinventory.inventoryservice.dto.AuthRequest;
import com.smartinventory.inventoryservice.security.JwtUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authManager,
                          JwtUtil jwtUtil,
                          UserDetailsService userDetailsService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest req) {

        // Authenticate username & password
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        // Load user
        UserDetails user = userDetailsService.loadUserByUsername(req.getUsername());

        // Extract role (first authority)
        String role = user.getAuthorities().iterator().next().getAuthority(); // e.g., ROLE_ADMIN

        // Generate JWT with role
        return jwtUtil.generateToken(user.getUsername(), role);
    }
}

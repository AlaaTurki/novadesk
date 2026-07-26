package com.alaaturki.novadesk.controller;

import com.alaaturki.novadesk.dto.LoginRequest;
import com.alaaturki.novadesk.dto.LoginResponse;
import com.alaaturki.novadesk.dto.RegisterRequest;
import com.alaaturki.novadesk.dto.RegisterResponse;
import com.alaaturki.novadesk.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }
}
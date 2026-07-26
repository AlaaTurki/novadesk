package com.alaaturki.novadesk.controller;


import com.alaaturki.novadesk.dto.*;
import com.alaaturki.novadesk.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;


    @PostMapping("/register")
    public RegisterResponse register(
            @RequestBody RegisterRequest request
    ){

        return authService.register(request);

    }


    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ){

        return authService.login(request);

    }

}
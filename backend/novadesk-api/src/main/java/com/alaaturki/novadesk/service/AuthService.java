package com.alaaturki.novadesk.service;


import com.alaaturki.novadesk.dto.*;
import com.alaaturki.novadesk.entity.User;
import com.alaaturki.novadesk.repository.UserRepository;
import com.alaaturki.novadesk.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;



    public JwtResponse register(RegisterRequest request){


        User user = new User();

        user.setUsername(request.getUsername());

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );


        userRepository.save(user);


        String token =
                jwtService.generateToken(user.getEmail());


        return new JwtResponse(token);

    }



    public JwtResponse login(LoginRequest request){


        User user =
                userRepository.findByEmail(request.getEmail())
                        .orElseThrow();


        if(!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )){

            throw new RuntimeException("Invalid password");

        }


        return new JwtResponse(
                jwtService.generateToken(user.getEmail())
        );


    }


}
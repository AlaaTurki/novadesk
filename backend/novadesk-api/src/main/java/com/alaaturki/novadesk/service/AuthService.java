package com.alaaturki.novadesk.service;


import com.alaaturki.novadesk.dto.LoginRequest;
import com.alaaturki.novadesk.dto.LoginResponse;
import com.alaaturki.novadesk.dto.RegisterRequest;
import com.alaaturki.novadesk.dto.RegisterResponse;
import com.alaaturki.novadesk.entity.Role;
import com.alaaturki.novadesk.entity.User;
import com.alaaturki.novadesk.repository.RoleRepository;
import com.alaaturki.novadesk.repository.UserRepository;
import com.alaaturki.novadesk.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;



    // =========================
    // REGISTER
    // =========================

    public RegisterResponse register(RegisterRequest request) {


        if (userRepository.existsByEmail(request.getEmail())) {

            throw new RuntimeException(
                    "Email already exists"
            );
        }



        Role userRole = roleRepository
                .findByName("USER")
                .orElseThrow(
                        () -> new RuntimeException(
                                "USER role not found"
                        )
                );



        User user = new User();


        user.setUsername(
                request.getUsername()
        );


        user.setEmail(
                request.getEmail()
        );


        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );


        user.setRole(
                userRole
        );



        User savedUser =
                userRepository.save(user);



        String token =
                jwtService.generateToken(savedUser);



        return new RegisterResponse(

                savedUser.getId(),

                savedUser.getUsername(),

                savedUser.getEmail(),

                token

        );

    }




    // =========================
    // LOGIN
    // =========================

    public LoginResponse login(LoginRequest request) {



        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(),

                        request.getPassword()

                )

        );



        User user =
                userRepository
                        .findByEmail(request.getEmail())
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User not found"
                                )
                        );



        String token =
                jwtService.generateToken(user);



        return new LoginResponse(

                user.getId(),

                user.getUsername(),

                user.getEmail(),

                token

        );

    }


}
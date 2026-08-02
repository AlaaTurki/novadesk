package com.alaaturki.novadesk.service;

import com.alaaturki.novadesk.dto.*;
import com.alaaturki.novadesk.entity.Role;
import com.alaaturki.novadesk.entity.User;
import com.alaaturki.novadesk.exception.BadRequestException;
import com.alaaturki.novadesk.repository.RoleRepository;
import com.alaaturki.novadesk.repository.UserRepository;
import com.alaaturki.novadesk.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {


    private static final Logger log =
            LoggerFactory.getLogger(AuthService.class);


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public RegisterResponse register(RegisterRequest request) {


        log.info("Register attempt email={} username={}",
                request.getEmail(),
                request.getUsername());


        if(userRepository.existsByEmail(request.getEmail())) {

            log.warn(
                    "Register failed: email already exists {}",
                    request.getEmail()
            );

            throw new BadRequestException(
                    "Email already exists"
            );
        }


        if(userRepository.existsByUsername(request.getUsername())) {


            log.warn(
                    "Register failed: username already exists {}",
                    request.getUsername()
            );


            throw new RuntimeException(
                    "Username already exists"
            );
        }



        Role role = roleRepository
                .findByName("USER")
                .orElseThrow(() ->
                        new RuntimeException(
                                "USER role not found"
                        )
                );



        User user = User.builder()

                .username(request.getUsername())

                .email(request.getEmail())

                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )

                .role(role)

                .build();



        User savedUser =
                userRepository.save(user);



        log.info(
                "User created successfully id={}",
                savedUser.getId()
        );



        String token =
                jwtService.generateToken(savedUser);



        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                token
        );

    }




    public LoginResponse login(LoginRequest request) {


        log.info(
                "Login attempt email={}",
                request.getEmail()
        );


        try {


            authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(

                            request.getEmail(),

                            request.getPassword()
                    )
            );


        } catch(Exception e) {


            log.error(
                    "Login failed for email={} reason={}",
                    request.getEmail(),
                    e.getMessage()
            );


            throw new RuntimeException(
                    "Invalid email or password"
            );

        }



        User user =
                userRepository
                        .findByEmail(request.getEmail())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );



        String token =
                jwtService.generateToken(user);



        log.info(
                "Login successful user={}",
                user.getUsername()
        );



        return new LoginResponse(

                user.getId(),

                user.getUsername(),

                user.getEmail(),

                token
        );

    }

}
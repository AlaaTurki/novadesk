package com.alaaturki.novadesk.service;


import com.alaaturki.novadesk.dto.UserProfileResponse;
import com.alaaturki.novadesk.entity.User;
import com.alaaturki.novadesk.exception.ResourceNotFoundException;
import com.alaaturki.novadesk.repository.UserRepository;


import lombok.RequiredArgsConstructor;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {



    private final UserRepository userRepository;




    public UserProfileResponse getCurrentUser(){



        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();



        String email =
                authentication.getName();



        User user =
                userRepository
                        .findByEmail(email)

                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "User not found"
                                )
                        );




        return new UserProfileResponse(

                user.getId(),

                user.getUsername(),

                user.getEmail(),

                user.getRole().getName()

        );


    }



}
package com.alaaturki.novadesk.controller;


import com.alaaturki.novadesk.dto.UserProfileResponse;
import com.alaaturki.novadesk.service.UserService;


import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {



    private final UserService userService;




    @GetMapping("/me")
    public UserProfileResponse getCurrentUser(){


        return userService.getCurrentUser();

    }


}
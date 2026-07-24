package com.alaaturki.novadesk.controller;


import com.alaaturki.novadesk.entity.User;
import com.alaaturki.novadesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/test/users")
@RequiredArgsConstructor
public class TestController {


    private final UserRepository userRepository;


    @GetMapping
    public List<User> getUsers(){

        return userRepository.findAll();

    }


    @PostMapping
    public User createUser(@RequestBody User user){

        return userRepository.save(user);

    }

}
package com.alaaturki.novadesk.controller;


import com.alaaturki.novadesk.dto.user.*;
import com.alaaturki.novadesk.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;



    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse create(
            @RequestBody CreateUserRequest request
    ){

        return userService.create(request);

    }




    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> findAll(){

        return userService.findAll();

    }



    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse findById(
            @PathVariable UUID id
    ){

        return userService.findById(id);

    }




    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateRole(
            @PathVariable UUID id,
            @RequestBody UpdateRoleRequest request
    ){

        return userService.updateRole(
                id,
                request
        );

    }




    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(
            @PathVariable UUID id
    ){

        userService.delete(id);

    }


}
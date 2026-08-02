package com.alaaturki.novadesk.controller;


import com.alaaturki.novadesk.entity.Role;
import com.alaaturki.novadesk.service.RoleService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/api/admin/roles")
@RequiredArgsConstructor
public class RoleController {


    private final RoleService service;



    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Role> all(){

        return service.findAll();

    }




    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Role get(
            @PathVariable UUID id
    ){

        return service.findById(id);

    }




    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Role create(
            @RequestBody Role role
    ){

        return service.create(role);

    }




    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(
            @PathVariable UUID id
    ){

        service.delete(id);

    }

}
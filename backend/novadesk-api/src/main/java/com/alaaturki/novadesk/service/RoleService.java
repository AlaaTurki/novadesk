package com.alaaturki.novadesk.service;


import com.alaaturki.novadesk.entity.Role;
import com.alaaturki.novadesk.exception.BadRequestException;
import com.alaaturki.novadesk.exception.ResourceNotFoundException;
import com.alaaturki.novadesk.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class RoleService {


    private final RoleRepository repository;



    public List<Role> findAll(){

        return repository.findAll();

    }



    public Role findById(UUID id){

        return repository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found"
                        )
                );

    }



    public Role create(Role role){


        if(repository.existsByName(role.getName())){

            throw new BadRequestException(
                    "Role already exists"
            );

        }


        return repository.save(role);

    }



    public void delete(UUID id){

        Role role=findById(id);


        repository.delete(role);

    }

}
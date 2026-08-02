package com.alaaturki.novadesk.service;


import com.alaaturki.novadesk.dto.user.*;
import com.alaaturki.novadesk.entity.Role;
import com.alaaturki.novadesk.entity.User;
import com.alaaturki.novadesk.repository.RoleRepository;
import com.alaaturki.novadesk.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;



    public UserResponse create(CreateUserRequest request){


        if(userRepository.existsByEmail(request.getEmail())){

            throw new RuntimeException(
                    "Email already exists"
            );
        }


        if(userRepository.existsByUsername(request.getUsername())){

            throw new RuntimeException(
                    "Username already exists"
            );
        }



        Role role =
                roleRepository
                        .findByName(request.getRole())
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Role not found"
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


        user.setRole(role);



        return map(
                userRepository.save(user)
        );

    }



    public List<UserResponse> findAll(){


        return userRepository.findAll()
                .stream()
                .map(this::map)
                .toList();

    }



    public UserResponse findById(UUID id){


        User user =
                userRepository.findById(id)
                        .orElseThrow(
                                ()->
                                        new RuntimeException(
                                                "User not found"
                                        )
                        );


        return map(user);

    }



    public UserResponse updateRole(
            UUID id,
            UpdateRoleRequest request
    ){


        User user =
                userRepository.findById(id)
                        .orElseThrow();



        Role role =
                roleRepository
                        .findByName(request.getRole())
                        .orElseThrow();



        user.setRole(role);


        return map(
                userRepository.save(user)
        );

    }



    public void delete(UUID id){


        userRepository.deleteById(id);

    }





    private UserResponse map(User user){


        return UserResponse.builder()

                .id(user.getId())

                .username(user.getUsername())

                .email(user.getEmail())

                .role(
                        user.getRole().getName()
                )

                .build();

    }


}
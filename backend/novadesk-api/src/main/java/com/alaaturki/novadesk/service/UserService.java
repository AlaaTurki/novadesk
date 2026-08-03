package com.alaaturki.novadesk.service;


import com.alaaturki.novadesk.dto.user.*;
import com.alaaturki.novadesk.entity.Role;
import com.alaaturki.novadesk.entity.User;
import com.alaaturki.novadesk.mapper.UserMapper;
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

    private final UserMapper mapper;



    public UserResponse create(
            CreateUserRequest request
    ){


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
                roleRepository.findByName(
                                request.getRole()
                                        .toUpperCase()
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Role not found"
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



        return mapper.toResponse(
                userRepository.save(user)
        );

    }




    public List<UserResponse> findAll(){

        return userRepository.findAll()

                .stream()

                .map(mapper::toResponse)

                .toList();

    }




    public UserResponse findById(
            UUID id
    ){


        User user =
                userRepository.findById(id)

                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User not found"
                                )
                        );


        return mapper.toResponse(user);

    }





    public UserResponse updateRole(
            UUID id,
            UpdateRoleRequest request
    ){


        User user =
                userRepository.findById(id)

                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User not found"
                                )
                        );



        Role role =
                roleRepository.findByName(
                                request.getRole()
                                        .toUpperCase()
                        )

                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Role not found"
                                )
                        );


        user.setRole(role);



        return mapper.toResponse(
                userRepository.save(user)
        );

    }




    public void delete(
            UUID id
    ){


        if(!userRepository.existsById(id)){

            throw new RuntimeException(
                    "User not found"
            );

        }


        userRepository.deleteById(id);

    }


}
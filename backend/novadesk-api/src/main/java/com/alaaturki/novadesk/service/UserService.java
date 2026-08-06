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





    /*
        CREATE USER
     */

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

                .username(
                        request.getUsername()
                )

                .email(
                        request.getEmail()
                )

                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )

                .role(role)

                .build();



        User saved =
                userRepository.save(user);



        return mapper.toResponse(saved);

    }







    /*
        GET ALL USERS
     */

    public List<UserResponse> findAll(){


        return userRepository.findAll()

                .stream()

                .map(mapper::toResponse)

                .toList();

    }








    /*
        GET USER BY ID
     */

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








    /*
        UPDATE USER INFORMATION
     */

    public UserResponse update(
            UUID id,
            UpdateUserRequest request
    ){

        User user =
                userRepository.findById(id)

                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User not found"
                                )
                        );


        if(request.getUsername()!=null){

            user.setUsername(
                    request.getUsername()
            );

        }



        if(request.getEmail()!=null){

            user.setEmail(
                    request.getEmail()
            );

        }



        if(request.getPassword()!=null
                &&
                !request.getPassword().isBlank()){


            user.setPassword(

                    passwordEncoder.encode(
                            request.getPassword()
                    )

            );

        }



        return mapper.toResponse(

                userRepository.save(user)

        );

    }









    /*
        CHANGE ROLE
     */

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



        String roleName =
                request.getRole()
                        .replace(
                                "ROLE_",
                                ""
                        )
                        .toUpperCase();



        Role role =

                roleRepository.findByName(
                                roleName
                        )

                        .orElseThrow(

                                () -> new RuntimeException(
                                        "Role not found: "
                                                + roleName
                                )

                        );



        user.setRole(role);



        return mapper.toResponse(

                userRepository.save(user)

        );

    }









    /*
        DELETE USER
     */

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
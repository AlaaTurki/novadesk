package com.alaaturki.novadesk.mapper;

import com.alaaturki.novadesk.dto.user.UserResponse;
import com.alaaturki.novadesk.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user){

        return UserResponse.builder()

                .id(user.getId())

                .username(user.getUsername())

                .email(user.getEmail())

                .role(user.getRole().getName())

                .createdAt(user.getCreatedAt())

                .updatedAt(user.getUpdatedAt())

                .build();

    }

}
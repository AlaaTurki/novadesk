package com.alaaturki.novadesk.dto.user;


import lombok.Data;


import java.util.UUID;


@Data
public class UpdateUserRequest {


    private String username;


    private String email;


    private UUID roleId;


}
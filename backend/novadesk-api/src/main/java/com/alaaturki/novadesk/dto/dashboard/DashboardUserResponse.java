package com.alaaturki.novadesk.dto.dashboard;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class DashboardUserResponse {


    private String username;


    private String email;


    private String role;


}
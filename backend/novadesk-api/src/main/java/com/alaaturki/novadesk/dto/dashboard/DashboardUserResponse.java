package com.alaaturki.novadesk.dto.dashboard;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.alaaturki.novadesk.dto.dashboard.DashboardUserResponse;

import org.springframework.data.jpa.repository.Query;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardUserResponse {


    private String username;


    private String email;


    private String role;


}


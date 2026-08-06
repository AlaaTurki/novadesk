package com.alaaturki.novadesk.controller;


import com.alaaturki.novadesk.dto.dashboard.DashboardStatsResponse;
import com.alaaturki.novadesk.dto.dashboard.DashboardUserResponse;
import com.alaaturki.novadesk.service.DashboardService;


import lombok.RequiredArgsConstructor;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {



    private final DashboardService dashboardService;




    @GetMapping("/stats")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public DashboardStatsResponse stats(){


        return dashboardService.getStats();


    }





    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<DashboardUserResponse> users(){


        return dashboardService.getUsers();


    }



}
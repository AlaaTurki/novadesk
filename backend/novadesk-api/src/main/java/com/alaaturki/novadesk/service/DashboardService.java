package com.alaaturki.novadesk.service;


import com.alaaturki.novadesk.dto.dashboard.DashboardUserResponse;
import com.alaaturki.novadesk.repository.UserRepository;


import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;


import java.util.List;



@Service
@RequiredArgsConstructor
public class DashboardService {



    private final UserRepository userRepository;



    public List<DashboardUserResponse> getUsers(){


        return userRepository.findDashboardUsers();


    }


}
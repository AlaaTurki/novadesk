package com.alaaturki.novadesk.service;


import com.alaaturki.novadesk.dto.dashboard.DashboardStatsResponse;
import com.alaaturki.novadesk.dto.dashboard.DashboardUserResponse;
import com.alaaturki.novadesk.entity.User;
import com.alaaturki.novadesk.repository.UserRepository;


import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;



import java.util.List;



@Service
@RequiredArgsConstructor
public class DashboardService {



    private final UserRepository userRepository;



    public DashboardStatsResponse getStats(){


        long totalUsers =
                userRepository.count();



        long admins =
                userRepository.countByRole_Name("ADMIN");



        long users =
                userRepository.countByRole_Name("USER");



        return new DashboardStatsResponse(

                totalUsers,

                admins,

                users,

                0

        );


    }




    public List<DashboardUserResponse> getUsers(){


        return userRepository.findAll()

                .stream()

                .map(user -> new DashboardUserResponse(

                        user.getUsername(),

                        user.getEmail(),

                        user.getRole().getName()

                ))

                .toList();


    }



}
package com.alaaturki.novadesk.config;

import com.alaaturki.novadesk.entity.Role;
import com.alaaturki.novadesk.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {


    private final RoleRepository roleRepository;


    @Override
    public void run(String... args) {


        if(roleRepository.findByName("USER").isEmpty()) {

            Role userRole = new Role();
            userRole.setName("USER");

            roleRepository.save(userRole);
        }



        if(roleRepository.findByName("ADMIN").isEmpty()) {

            Role adminRole = new Role();
            adminRole.setName("ADMIN");

            roleRepository.save(adminRole);
        }


    }
}
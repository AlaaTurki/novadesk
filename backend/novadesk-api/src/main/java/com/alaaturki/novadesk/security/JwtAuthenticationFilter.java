package com.alaaturki.novadesk.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import lombok.RequiredArgsConstructor;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;


import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;


import org.springframework.stereotype.Component;


import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;



@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter
        extends OncePerRequestFilter {


    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;



    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )
            throws ServletException, IOException {



        String path = request.getServletPath();



        // ignore login/register
        if(path.startsWith("/api/auth")){

            filterChain.doFilter(request,response);
            return;

        }



        String authHeader =
                request.getHeader("Authorization");



        if(authHeader == null ||
                !authHeader.startsWith("Bearer ")){

            System.out.println(
                    "No JWT token found"
            );


            filterChain.doFilter(
                    request,
                    response
            );

            return;

        }



        String jwt =
                authHeader.substring(7);



        try {


            String email =
                    jwtService.extractUsername(jwt);



            System.out.println(
                    "JWT email = " + email
            );



            if(email != null &&
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication()==null){



                UserDetails userDetails =
                        userDetailsService
                                .loadUserByUsername(email);



                System.out.println(
                        "User found : "
                                + userDetails.getUsername()
                );



                if(jwtService.isTokenValid(
                        jwt,
                        userDetails
                )){


                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );



                    authentication.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );



                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authentication);



                    System.out.println(
                            "JWT Authentication SUCCESS"
                    );

                }

            }



        }
        catch(Exception e){


            System.out.println(
                    "JWT ERROR : "
                            + e.getMessage()
            );

        }



        filterChain.doFilter(
                request,
                response
        );

    }


}
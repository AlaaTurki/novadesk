package com.alaaturki.novadesk.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private static final Logger log =
            LoggerFactory.getLogger(JwtAuthenticationFilter.class);


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


        log.info("Request path : {}", path);



        if(path.startsWith("/api/auth")){

            log.info("Auth endpoint - skipping JWT");

            filterChain.doFilter(request,response);
            return;
        }



        String authHeader =
                request.getHeader("Authorization");



        log.info("Authorization Header : {}", authHeader);



        if(authHeader == null ||
                !authHeader.startsWith("Bearer ")){

            log.warn("No JWT token found");

            filterChain.doFilter(request,response);
            return;
        }



        String jwt =
                authHeader.substring(7);



        try {


            String email =
                    jwtService.extractUsername(jwt);



            log.info(
                    "JWT username/email : {}",
                    email
            );



            if(email != null &&
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication()==null){



                UserDetails userDetails =
                        userDetailsService
                                .loadUserByUsername(email);



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



                    log.info(
                            "JWT Authentication SUCCESS for {}",
                            email
                    );


                }
                else {

                    log.warn(
                            "Invalid JWT token"
                    );

                }

            }


        }
        catch(Exception e){

            log.error(
                    "JWT Error : {}",
                    e.getMessage()
            );

        }



        filterChain.doFilter(
                request,
                response
        );

    }

}
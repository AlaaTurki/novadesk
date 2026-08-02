package com.alaaturki.novadesk.service;


import com.alaaturki.novadesk.dto.CreateTicketRequest;
import com.alaaturki.novadesk.dto.TicketResponse;

import com.alaaturki.novadesk.entity.*;

import com.alaaturki.novadesk.exception.ResourceNotFoundException;

import com.alaaturki.novadesk.repository.TicketRepository;
import com.alaaturki.novadesk.repository.UserRepository;


import lombok.RequiredArgsConstructor;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class TicketService {



    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;



    private User currentUser(){


        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();


        return userRepository
                .findByEmail(authentication.getName())

                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "User not found"
                        )
                );


    }



    public TicketResponse create(
            CreateTicketRequest request
    ){


        Ticket ticket =
                Ticket.builder()

                        .title(request.getTitle())

                        .description(
                                request.getDescription()
                        )

                        .priority(
                                request.getPriority() == null
                                        ?
                                        TicketPriority.MEDIUM
                                        :
                                        request.getPriority()
                        )

                        .status(
                                TicketStatus.OPEN
                        )

                        .createdBy(
                                currentUser()
                        )

                        .build();



        Ticket saved =
                ticketRepository.save(ticket);



        return map(saved);

    }




    public List<TicketResponse> findMyTickets(){


        return ticketRepository
                .findByCreatedBy(currentUser())

                .stream()

                .map(this::map)

                .toList();

    }




    public TicketResponse findById(
            java.util.UUID id
    ){


        Ticket ticket =
                ticketRepository
                        .findById(id)

                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Ticket not found"
                                )
                        );


        return map(ticket);

    }





    public void delete(
            java.util.UUID id
    ){


        Ticket ticket =
                ticketRepository
                        .findById(id)

                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Ticket not found"
                                )
                        );


        ticketRepository.delete(ticket);

    }




    private TicketResponse map(
            Ticket ticket
    ){


        return new TicketResponse(

                ticket.getId(),

                ticket.getTitle(),

                ticket.getDescription(),

                ticket.getStatus(),

                ticket.getPriority()

        );


    }


}
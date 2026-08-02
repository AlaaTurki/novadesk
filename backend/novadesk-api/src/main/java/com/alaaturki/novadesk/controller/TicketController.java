package com.alaaturki.novadesk.controller;


import com.alaaturki.novadesk.dto.CreateTicketRequest;
import com.alaaturki.novadesk.dto.TicketResponse;

import com.alaaturki.novadesk.service.TicketService;


import jakarta.validation.Valid;


import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {



    private final TicketService ticketService;



    @PostMapping
    public TicketResponse create(

            @Valid
            @RequestBody CreateTicketRequest request

    ){

        return ticketService.create(request);

    }




    @GetMapping
    public List<TicketResponse> myTickets(){

        return ticketService.findMyTickets();

    }




    @GetMapping("/{id}")
    public TicketResponse findById(

            @PathVariable UUID id

    ){

        return ticketService.findById(id);

    }




    @DeleteMapping("/{id}")
    public String delete(

            @PathVariable UUID id

    ){

        ticketService.delete(id);

        return "Ticket deleted";

    }


}
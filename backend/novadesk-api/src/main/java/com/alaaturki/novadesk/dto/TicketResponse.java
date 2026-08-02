package com.alaaturki.novadesk.dto;


import com.alaaturki.novadesk.entity.TicketPriority;
import com.alaaturki.novadesk.entity.TicketStatus;


import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.UUID;



@Getter
@AllArgsConstructor
public class TicketResponse {



    private UUID id;


    private String title;


    private String description;


    private TicketStatus status;


    private TicketPriority priority;


}
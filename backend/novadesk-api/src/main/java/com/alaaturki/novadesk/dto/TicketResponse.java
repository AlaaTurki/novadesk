package com.alaaturki.novadesk.dto;

import com.alaaturki.novadesk.entity.TicketPriority;
import com.alaaturki.novadesk.entity.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {

    private UUID id;

    private String title;

    private String description;

    private TicketStatus status;

    private TicketPriority priority;

    private String createdBy;

    private String assignedTo;
}
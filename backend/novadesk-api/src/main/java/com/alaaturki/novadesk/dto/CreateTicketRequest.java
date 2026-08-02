package com.alaaturki.novadesk.dto;


import com.alaaturki.novadesk.entity.TicketPriority;


import jakarta.validation.constraints.NotBlank;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CreateTicketRequest {



    @NotBlank(
            message = "Title is required"
    )
    private String title;



    @NotBlank(
            message = "Description is required"
    )
    private String description;



    private TicketPriority priority;


}
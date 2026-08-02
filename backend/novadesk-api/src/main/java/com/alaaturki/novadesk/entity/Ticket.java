package com.alaaturki.novadesk.entity;


import jakarta.persistence.*;

import lombok.*;


import java.util.UUID;



@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket extends BaseEntity {


    @Column(nullable = false)
    private String title;



    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketPriority priority;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;


}
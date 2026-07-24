package com.alaaturki.novadesk.entity;


import jakarta.persistence.*;
import lombok.*;
import com.alaaturki.novadesk.enums.TicketStatus;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name="tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(nullable = false)
    private String title;


    @Column(columnDefinition = "TEXT")
    private String description;


    @Enumerated(EnumType.STRING)
    private TicketStatus status;


    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name="created_by")
    private User createdBy;



    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();

        if(status == null){
            status = TicketStatus.OPEN;
        }
    }

}
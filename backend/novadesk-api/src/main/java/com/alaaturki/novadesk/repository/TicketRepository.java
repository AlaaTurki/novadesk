package com.alaaturki.novadesk.repository;


import com.alaaturki.novadesk.entity.Ticket;

import com.alaaturki.novadesk.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.UUID;



public interface TicketRepository
        extends JpaRepository<Ticket, UUID> {



    List<Ticket> findByCreatedBy(User user);


}
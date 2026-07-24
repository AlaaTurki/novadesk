package com.alaaturki.novadesk.repository;

import com.alaaturki.novadesk.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}
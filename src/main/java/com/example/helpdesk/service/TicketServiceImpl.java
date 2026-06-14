package com.example.helpdesk.service;

import com.example.helpdesk.dto.TicketCreateDto;
import com.example.helpdesk.model.Ticket;
import com.example.helpdesk.model.TicketStatus;
import com.example.helpdesk.repository.TicketRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Ticket createTicket(TicketCreateDto dto) {
        Ticket ticket = new Ticket();
        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setCustomerName(dto.getCustomerName());
        ticket.setStatus(TicketStatus.NEW);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found: " + id));
    }
}
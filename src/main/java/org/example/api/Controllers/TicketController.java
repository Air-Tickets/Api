package org.example.api.Controllers;

import org.example.api.Models.TicketDTO;
import org.example.api.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    TicketRepository ticketRepository;

    @GetMapping("")
    public List<TicketDTO> getTickets(){
        return ticketRepository.getAllTickets();
    }
}


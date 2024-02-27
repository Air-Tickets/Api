package org.example.api.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.JSONPObject;
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

    @GetMapping("takenSeats/{id}")
    public List<String> getTakenSeats(@PathVariable("id")String id) {return ticketRepository.getAllTakenSeats(id);}

    @PutMapping("buyTicket")
    public boolean buyTicket(@RequestBody String json) throws JsonProcessingException {return ticketRepository.addTicket(json);}
}


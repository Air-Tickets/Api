package org.example.api.Controllers;

import org.example.api.Models.FlightDTO;
import org.example.api.Models.TicketDTO;
import org.example.api.Repositories.FlightRepository;
import org.example.api.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    FlightRepository flightRepository;

    @CrossOrigin
    @GetMapping("")
    public List<FlightDTO> getFlights(){
        return flightRepository.getAllFlights();
    }
}


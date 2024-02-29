package org.example.api.Controllers;

import org.example.api.Models.FlightDTO;
import org.example.api.Repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    FlightRepository flightRepository;

    //ZWRACA: lista wszystkich lotów
    //ZASTOSOWANIE: DesktopApp - dostępne loty, Website - terminal lotów
    @CrossOrigin
    @GetMapping("")
    public List<FlightDTO> getFlights(){
        return flightRepository.getAllFlights();
    }
}


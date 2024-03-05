package org.example.api.Controllers;

import org.example.api.Models.TicketDTO;
import org.example.api.Repositories.TicketRepository;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    TicketRepository ticketRepository;

    //ZWRACA: lista wszystkich biletów
    @GetMapping("")
    public List<TicketDTO> getTickets(){
        return ticketRepository.getAllTickets();
    }

    //URL: przyjmuje id lotu
    //ZWRACA: lista stringów zajętych miejsc danego lotu
    //ZASTOSOWANIE: DesktopApp - wyszukiwanie wolnych miejsc
    //NOTATKI: tak, jest to nieintuicyjne, że znajduje się to w kontrolerze biletów, a nie lotów, ale tak wygląda baza danych, co poradzić
    @GetMapping("takenSeats/{id}")
    public List<String> getTakenSeats(@PathVariable("id")String id) {return ticketRepository.getAllTakenSeats(id);}

    //BODY: przyjmuje obiekt biletu
    //DZIAŁANIE: dodanie biletu do bazy
    //ZWRACA: true jeśli działanie zakończyło się sukcesem
    //ZASTOSOWANIE: DesktopApp - kupowanie biletów
    @PutMapping("buyTicket")
    public boolean buyTicket(@RequestBody String json) throws ParseException {return ticketRepository.addTicket(json);}

    //URL: przekazuje kod kreskowy biletu
    //ZWRACA: bilet
    @GetMapping("scan/{code}")
    public TicketDTO getTicketByCode(@PathVariable("code") String code) {return ticketRepository.getTicketById(code);}
}


package org.example.api.Repositories;

import org.example.api.BarCodeGenerator;
import org.example.api.Models.TicketDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String fromMail;

    //FUNKCJA WYSYŁAJĄCA MAILA
    protected void mailSend(String toMail, String text, String subject){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        simpleMailMessage.setTo(toMail);

        mailSender.send(simpleMailMessage);
    }



    //ZWRACA: listę biletów
    public List<TicketDTO> getAllTickets() {
        return jdbcTemplate.query("SELECT * FROM bilety", BeanPropertyRowMapper.newInstance(TicketDTO.class));
    }

    //PRZYJMUJE: id lotu
    //ZWRACA: listę stringów zajętych miejsc
    public List<String> getAllTakenSeats(String id) {
        List<TicketDTO> objects = jdbcTemplate.query("SELECT Miejsce_w_samolocie FROM bilety WHERE id_lotu = ?", BeanPropertyRowMapper.newInstance(TicketDTO.class), id);
        List<String> takenSeats = new ArrayList<>();
        for (TicketDTO ticket : objects) {
            takenSeats.add(ticket.getMiejsce_w_samolocie());
        }
        return takenSeats;
    }

    //PRZYJMUJE: string jjsonowy obiektu biletu
    //DZIAŁANIE: dodaje bilet do bazy
    //ZWRACA: true, jeśli diałanie zakończono pomyślnie
    public boolean addTicket(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject ticket = (JSONObject) parser.parse(json);
        String id;
        do {
            id = BarCodeGenerator.generate();
        } while (jdbcTemplate.queryForObject("SELECT EXISTS(SELECT * FROM bilety WHERE id = ?)", new Object [] {id}, Boolean.class));
        jdbcTemplate.update("INSERT INTO bilety VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                id,
                ticket.get("id_uzytkownika"),
                ticket.get("Imie_na_bilecie").toString(),
                ticket.get("Nazwisko_na_bilecie").toString(),
                ticket.get("Pesel_na_bilecie").toString(),
                ticket.get("id_lotu"),
                ticket.get("Miejsce_w_samolocie").toString(),
                ticket.get("Maksymalna_waga_bagazu"));

        //POBIERANIE MAILA CZYLI LOGINU UŻYTKOWNIKA ŻEBY WIEDZIEĆ GDZIE WYSYŁAĆ MAILA
        List<String> email = jdbcTemplate.queryForList("SELECT Login FROM uzytkownicy WHERE id = ?",  String.class, ticket.get("id_uzytkownika"));
        String usersEmail = email.get(0);
        String subject = "New payment!";
        String text = "Hello,"
                +"\nThis email is payment confirmation for your ticket. You can find your ticket on your mobile app"
                +"\n"
                +"\nHave a nice flight " + ticket.get("Imie_na_bilecie").toString() + " " + ticket.get("Nazwisko_na_bilecie").toString()
                +"\n"
                +"\nBest regards, A.M.E.L.K.A. team";
        mailSend(usersEmail, text, subject);
        return true;
    }

    //PRZYJMUJE: id biletu
    //ZWRACA: bilet
    public TicketDTO getTicketById(String id){
        return jdbcTemplate.queryForObject("SELECT * FROM bilety WHERE id = ?", BeanPropertyRowMapper.newInstance(TicketDTO.class), id);
    }
}

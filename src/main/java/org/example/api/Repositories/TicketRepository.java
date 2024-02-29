package org.example.api.Repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.example.api.Models.TicketDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<TicketDTO> getAllTickets() {
        return jdbcTemplate.query("SELECT * FROM bilety", BeanPropertyRowMapper.newInstance(TicketDTO.class));
    }

    public List<String> getAllTakenSeats(String id) {
        List<TicketDTO> objects = jdbcTemplate.query("SELECT Miejsce_w_samolocie FROM bilety WHERE id_lotu = ?", BeanPropertyRowMapper.newInstance(TicketDTO.class), id);
        List<String> takenSeats = new ArrayList<>();
        for (TicketDTO ticket : objects) {
            takenSeats.add(ticket.getMiejsce_w_samolocie());
        }
        return takenSeats;
    }

    public boolean addTicket(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject ticket = (JSONObject) parser.parse(json);
        TicketDTO maxId = jdbcTemplate.queryForObject("SELECT Id FROM bilety ORDER BY Id DESC LIMIT 1", BeanPropertyRowMapper.newInstance(TicketDTO.class));
        assert maxId != null;
        jdbcTemplate.update("INSERT INTO bilety VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                maxId.getId()+1,
                ticket.get("id_uzytkownika"),
                ticket.get("Imie_na_bilecie").toString(),
                ticket.get("Nazwisko_na_bilecie").toString(),
                ticket.get("Pesel_na_bilecie"),
                ticket.get("id_lotu"),
                ticket.get("Miejsce_w_samolocie").toString(),
                ticket.get("Maksymalna_waga_bagazu"));
        return true;
    }
}

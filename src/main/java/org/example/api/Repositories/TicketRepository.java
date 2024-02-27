package org.example.api.Repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.api.Models.TicketDTO;
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

    public boolean addTicket(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TicketDTO ticket = objectMapper.readValue(json, TicketDTO.class);
        TicketDTO maxId = jdbcTemplate.queryForObject("SELECT Id FROM bilety SORT BY Id DESC LIMIT 1", BeanPropertyRowMapper.newInstance(TicketDTO.class));
        assert maxId != null;
        jdbcTemplate.update("INSERT INTO bilety VALUES ?, ?, ?, ?, ?, ?, ?, ?",
                maxId.getId()+1,
                ticket.getId_uzytkownika(),
                ticket.getImie_na_bilecie(),
                ticket.getNazwisko_na_bilecie(),
                ticket.getPesel_na_bilecie(),
                ticket.getId_lotu(),
                ticket.getMiejsce_w_samolocie(),
                ticket.getMaksymalna_waga_bagazu());
        return true;
    }
}

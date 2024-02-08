package org.example.api.Repositories;

import org.example.api.Models.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TicketRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<TicketDTO> getAllTickets(){
        return jdbcTemplate.query("SELECT * FROM Tickets", BeanPropertyRowMapper.newInstance(TicketDTO.class));
    }
}

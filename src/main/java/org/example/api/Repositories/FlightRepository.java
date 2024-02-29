package org.example.api.Repositories;

import org.example.api.Models.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlightRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //ZWRACA: listę lotów
    public List<FlightDTO> getAllFlights(){
        return jdbcTemplate.query("SELECT * FROM lot", BeanPropertyRowMapper.newInstance(FlightDTO.class));
    }
}

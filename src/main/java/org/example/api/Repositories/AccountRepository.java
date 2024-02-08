package org.example.api.Repositories;

import org.example.api.Models.AccountDTO;
import org.example.api.Models.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class AccountRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<AccountDTO> getAllAccounts(){
        return jdbcTemplate.query("SELECT * FROM uzytkownicy", BeanPropertyRowMapper.newInstance(AccountDTO.class));
    }

    public boolean login(String login, String password){
        AccountDTO account = jdbcTemplate.queryForObject("SELECT * FROM uzytkownicy WHERE login = ?",  BeanPropertyRowMapper.newInstance(AccountDTO.class), login);
        assert account != null;
        return (Objects.equals(account.getPassword(), password));
    }
}

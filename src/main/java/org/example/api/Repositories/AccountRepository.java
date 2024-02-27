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

    public AccountDTO login(String login, String password){
        List<AccountDTO> account = jdbcTemplate.query("SELECT * FROM uzytkownicy WHERE login = ?",  BeanPropertyRowMapper.newInstance(AccountDTO.class), login);
        if(!account.isEmpty() && Objects.equals(account.get(0).getHaslo(), password)){
            return account.get(0);
        }
        return null;
    }
}

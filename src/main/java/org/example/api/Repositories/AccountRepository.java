package org.example.api.Repositories;

import org.example.api.Models.AccountDTO;
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

    //ZWRACA: listę wszystkich kont
    public List<AccountDTO> getAllAccounts(){
        return jdbcTemplate.query("SELECT * FROM uzytkownicy", BeanPropertyRowMapper.newInstance(AccountDTO.class));
    }

    //PRZYJMUJE: login i hasło
    //ZWRACA: konto użytkownika - jeśli logowanie skuteczne / pusty obiekt konta - w przeciwnym wypadku
    public AccountDTO login(String login, String password){
        List<AccountDTO> account = jdbcTemplate.query("SELECT * FROM uzytkownicy WHERE login = ?",  BeanPropertyRowMapper.newInstance(AccountDTO.class), login);
        if(!account.isEmpty() && Objects.equals(account.get(0).getHaslo(), password)){
            return account.get(0);
        }
        return null;
    }
}

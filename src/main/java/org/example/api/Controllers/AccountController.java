package org.example.api.Controllers;

import org.example.api.Models.AccountDTO;
import org.example.api.Models.TicketDTO;
import org.example.api.Repositories.AccountRepository;
import org.example.api.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("")
    public List<AccountDTO> getAccounts(){
        return accountRepository.getAllAccounts();
    }

    @GetMapping("/login/{login}")
    public boolean logIn(@PathVariable("login")String login, @RequestBody String password) {
        return accountRepository.login(login, password);
    }
}


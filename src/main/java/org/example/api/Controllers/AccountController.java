package org.example.api.Controllers;

import org.example.api.Models.AccountDTO;
import org.example.api.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    //ZWRACA: lista wszystkich użytkowników
    @GetMapping("")
    public List<AccountDTO> getAccounts(){
        return accountRepository.getAllAccounts();
    }

    //URL: przyjmuje login użytkownika
    //BODY: przyjmuje hasło użytkownika
    //ZWRACA: konto użytkownika, jeśli hasło i login są poprawne, lub pusty obiekt
    //ZASTOSOWANIE DesktopApp, MobileApp - logowanie do aplikacji
    @PostMapping("/login/{login}")
    public AccountDTO logIn(@PathVariable("login")String login, @RequestBody String password) {
        return accountRepository.login(login, password);
    }
}


package org.example.api.Models;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountDTO {
    @Id
    Integer id;
    String Imie;
    String Nazwisko;
    String Pesel;
    String Login;
    String Haslo;

}

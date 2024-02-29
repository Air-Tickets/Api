package org.example.api.Models;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketDTO {
    @Id
    String id;
    Integer id_uzytkownika;
    String Imie_na_bilecie;
    String Nazwisko_na_bilecie;
    String Pesel_na_bilecie;
    Integer id_lotu;
    String Miejsce_w_samolocie;
    Integer Maksymalna_waga_bagazu;
}

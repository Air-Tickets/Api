package org.example.api.Models;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightDTO {
    @Id
    Integer id;
    String Miejsce_startowe;
    String Miejsce_docelowe;
    String Data;
    String Godzina_odlotu;
    String Godzina_boardingu;
    String Godzina_w_miejscu_docelowym;
    String Przewoznik;
    Integer Numer_lotu;
    Integer Id_samolotu;
    Integer Liczba_kupionych_biletow;
    Integer Id_zalogi;
}

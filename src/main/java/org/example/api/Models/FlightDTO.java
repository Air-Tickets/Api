package org.example.api.Models;

import jakarta.persistence.Id;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMiejsce_startowe() {
        return Miejsce_startowe;
    }

    public void setMiejsce_startowe(String miejsce_startowe) {
        Miejsce_startowe = miejsce_startowe;
    }

    public String getMiejsce_docelowe() {
        return Miejsce_docelowe;
    }

    public void setMiejsce_docelowe(String miejsce_docelowe) {
        Miejsce_docelowe = miejsce_docelowe;
    }

    public String getGodzina_odlotu() {
        return Godzina_odlotu;
    }

    public void setGodzina_odlotu(String godzina_odlotu) {
        Godzina_odlotu = godzina_odlotu;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getGodzina_boardingu() {
        return Godzina_boardingu;
    }

    public void setGodzina_boardingu(String godzina_boardingu) {
        Godzina_boardingu = godzina_boardingu;
    }

    public String getGodzina_w_miejscu_docelowym() {
        return Godzina_w_miejscu_docelowym;
    }

    public void setGodzina_w_miejscu_docelowym(String godzina_w_miejscu_docelowym) {
        Godzina_w_miejscu_docelowym = godzina_w_miejscu_docelowym;
    }

    public String getPrzewoznik() {
        return Przewoznik;
    }

    public void setPrzewoznik(String przewoznik) {
        Przewoznik = przewoznik;
    }

    public Integer getNumer_lotu() {
        return Numer_lotu;
    }

    public void setNumer_lotu(Integer numer_lotu) {
        Numer_lotu = numer_lotu;
    }

    public Integer getId_samolotu() {
        return Id_samolotu;
    }

    public void setId_samolotu(Integer id_samolotu) {
        Id_samolotu = id_samolotu;
    }

    public Integer getLiczba_kupionych_biletow() {
        return Liczba_kupionych_biletow;
    }

    public void setLiczba_kupionych_biletow(Integer liczba_kupionych_biletow) {
        Liczba_kupionych_biletow = liczba_kupionych_biletow;
    }

    public Integer getId_zalogi() {
        return Id_zalogi;
    }

    public void setId_zalogi(Integer id_zalogi) {
        Id_zalogi = id_zalogi;
    }
}

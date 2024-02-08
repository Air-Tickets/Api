package org.example.api.Models;

import jakarta.persistence.Id;

public class TicketDTO {
    @Id
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

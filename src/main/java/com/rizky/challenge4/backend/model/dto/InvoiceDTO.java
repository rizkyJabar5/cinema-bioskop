package com.rizky.challenge4.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvoiceDTO {

    private String username;
    private String titleFilm;
    private String seatsOrdered;
    private String scheduleDate;
    private String scheduleTime;
    private String getPrice;

}

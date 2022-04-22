package com.rizky.challenge4.backend.model.mapper;

import com.rizky.challenge4.backend.model.dto.InvoiceDTO;
import com.rizky.challenge4.backend.model.entity.HistoryTransaction;
import org.springframework.stereotype.Component;

@Component
public class RequestCreateInvoiceDto {

    public InvoiceDTO createInvoice(HistoryTransaction history) {

        InvoiceDTO invoice = new InvoiceDTO();
        invoice.setUsername(history.getUsers().getUsername());
        invoice.setScheduleDate(history.getSchedule().getShowDate().toString());
        invoice.setScheduleTime(history.getSchedule().getStartTime().toString());
        invoice.setTitleFilm(history.getFilm().getTitle());
        invoice.setSeatsOrdered(history.getSeats().getSeatsRow().toString());
        invoice.setSeatsOrdered(history.getSeats().getSeatsNumber().toString());
        invoice.setGetPrice(history.getSchedule().getPrice().toString());

        return invoice;
    }

}

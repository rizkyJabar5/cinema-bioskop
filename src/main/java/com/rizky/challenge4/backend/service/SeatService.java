package com.rizky.challenge4.backend.service;

import com.rizky.challenge4.backend.model.enums.SeatsRowEnum;
import com.rizky.challenge4.backend.model.dto.SeatsDto;
import com.rizky.challenge4.backend.model.entity.Seats;

import java.util.List;

public interface SeatService {

    void addNewSeats(SeatsDto seats);

    Seats findById(Long id);

    List<SeatsDto> findAllSeats(Seats seat);

    Seats findSeatsBySeatsNumberId(Long seatsNumber, SeatsRowEnum seatsRow);
}

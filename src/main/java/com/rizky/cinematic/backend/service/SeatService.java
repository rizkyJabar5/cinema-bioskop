package com.rizky.cinematic.backend.service;

import com.rizky.cinematic.backend.model.dto.SeatsDto;
import com.rizky.cinematic.backend.model.entity.Seats;
import com.rizky.cinematic.backend.model.enums.SeatsRowEnum;

import java.util.List;

public interface SeatService {

    void addNewSeats(SeatsDto seats);

    Seats findById(Long id);

    List<SeatsDto> findAllSeats(Seats seat);

    Seats findSeatsBySeatsNumberId(Long seatsNumber, SeatsRowEnum seatsRow);
}

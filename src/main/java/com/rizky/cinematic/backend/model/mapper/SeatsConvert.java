package com.rizky.cinematic.backend.model.mapper;

import com.rizky.cinematic.backend.model.dto.SeatsDto;
import com.rizky.cinematic.backend.model.entity.Seats;
import com.rizky.cinematic.backend.model.entity.SeatsNumberID;
import org.springframework.stereotype.Component;

@Component
public class SeatsConvert {

    public Seats createSeats (SeatsDto dto) {

        Seats seats = new Seats();
        seats.setSeatsNumberId(dto.getSeatsId());

        SeatsNumberID seatsNumber = new SeatsNumberID();
        if(isaBoolean(seatsNumber)){
            seatsNumber.setSeatsNumber(seatsNumber.getSeatsNumber());
            seatsNumber.setSeatsRow(seatsNumber.getSeatsRow());
        } else {
            seatsNumber.setSeatsNumber(dto.getSeatsId().getSeatsNumber());
            seatsNumber.setSeatsRow(dto.getSeatsId().getSeatsRow());
        }
        return seats;
    }

    public SeatsDto getSeats(Seats entity) {

        SeatsDto seats = new SeatsDto();
        seats.setId(entity.getId());
        seats.setSeatsId(entity.getSeatsNumberId());

        return seats;
    }

    private boolean isaBoolean(SeatsNumberID seatsNumber) {
        return seatsNumber.getSeatsNumber() == null && seatsNumber.getSeatsRow() == null;
    }
}

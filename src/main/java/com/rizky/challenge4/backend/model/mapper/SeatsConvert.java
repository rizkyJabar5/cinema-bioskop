package com.rizky.challenge4.backend.model.mapper;

import com.rizky.challenge4.backend.model.dto.SeatsDto;
import com.rizky.challenge4.backend.model.entity.Seats;
import com.rizky.challenge4.backend.model.entity.SeatsNumberID;
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
//        TODO: LOGIC if the seats have not been taken, the seats can be taken or set.
        return seats;
    }

    private boolean isaBoolean(SeatsNumberID seatsNumber) {
        return seatsNumber.getSeatsNumber() == null && seatsNumber.getSeatsRow() == null;
    }
}

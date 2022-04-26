package com.rizky.challenge4.backend.service.impl;

import com.rizky.challenge4.backend.model.SeatsRowEnum;
import com.rizky.challenge4.backend.model.dto.SeatsDto;
import com.rizky.challenge4.backend.model.entity.Seats;
import com.rizky.challenge4.backend.model.mapper.SeatsConvert;
import com.rizky.challenge4.backend.repository.SeatsRepository;
import com.rizky.challenge4.backend.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatsRepository repository;
    private final SeatsConvert converter;

    @Autowired
    public SeatServiceImpl(SeatsRepository repository, SeatsConvert converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public void addNewSeats(SeatsDto seats) {
       repository.save(converter.createSeats(seats));
    }

    @Override
    public Seats findById(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public List<SeatsDto> findAllSeats(Seats seat) {
        return repository.findAll()
                .stream()
                .map(converter::getSeats)
                .collect(Collectors.toList());
    }

    @Override
    public Seats findSeatsBySeatsNumberId(Long seatsNumber, SeatsRowEnum seatsRow) {
        return repository.findSeatsBySeatsNumberId(seatsNumber, seatsRow);
    }

}

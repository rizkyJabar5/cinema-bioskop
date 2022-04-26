package com.rizky.challenge4.backend.controller;

import com.rizky.challenge4.backend.model.dto.SeatsDto;
import com.rizky.challenge4.backend.model.entity.Seats;
import com.rizky.challenge4.backend.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/seats")
public class SeatController {

    @Autowired
    private SeatService service;

    @PostMapping("add-seats")
    public ResponseEntity<String> createSeats(SeatsDto dto) {
        service.addNewSeats(dto);
        return ResponseEntity.accepted().body(dto.toString());
    }

    @GetMapping("/showall")
    public ResponseEntity<List<SeatsDto>> getAllSeatsOnStore(Seats seat) {

        return ResponseEntity.ok().body(service.findAllSeats(seat));
    }

}

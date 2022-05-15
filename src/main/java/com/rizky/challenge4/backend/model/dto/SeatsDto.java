package com.rizky.challenge4.backend.model.dto;

import com.rizky.challenge4.backend.model.enums.SeatsRowEnum;
import com.rizky.challenge4.backend.model.entity.SeatsNumberID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SeatsDto {

    private Long id;
    private SeatsNumberID seatsId;
    private boolean available;

    private Long seatsNumber;
    private SeatsRowEnum seatsRow;

    public SeatsDto(SeatsNumberID seatsId) {
        this.seatsId = seatsId;
    }
}

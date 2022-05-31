package com.rizky.cinematic.backend.model.dto;

import com.rizky.cinematic.backend.model.entity.SeatsNumberID;
import com.rizky.cinematic.backend.model.enums.SeatsRowEnum;
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

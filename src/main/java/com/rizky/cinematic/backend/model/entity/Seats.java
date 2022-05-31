package com.rizky.cinematic.backend.model.entity;

import com.rizky.cinematic.backend.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seats")
public class Seats extends AbstractEntity {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "seatsNumber", column = @Column(name = "seats_number")),
            @AttributeOverride(name = "seatsRow", column = @Column(name = "seats_row"))
    })
    private SeatsNumberID seatsNumberId;

    private boolean available;

    public Seats(SeatsNumberID seatsNumber) {
        this.seatsNumberId = seatsNumber;
    }
}

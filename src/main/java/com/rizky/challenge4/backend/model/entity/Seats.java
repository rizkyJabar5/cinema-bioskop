package com.rizky.challenge4.backend.model.entity;

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
public class Seats {

    @EmbeddedId
    @AttributeOverride(name = "seatsNumber", column = @Column(name = "seats_number"))
    @AttributeOverride(name = "seatsRow", column = @Column(name = "seats_row"))
    private SeatsNumberID seatsNumberId;

    private boolean available;

}

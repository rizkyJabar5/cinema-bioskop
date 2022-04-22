package com.rizky.challenge4.backend.model.entity;

import com.rizky.challenge4.backend.model.SeatsRow;
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
    @AttributeOverride(name = "seatsID", column = @Column(name = "seats_id"))
    @AttributeOverride(name = "seatsNumber", column = @Column(name = "seats_number"))
    private SeatsNumberID seatsNumber;

    @Column(name = "seats")
    @Enumerated(EnumType.STRING)
    private SeatsRow seatsRow;

}

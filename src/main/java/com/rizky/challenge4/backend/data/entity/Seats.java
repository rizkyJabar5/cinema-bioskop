package com.rizky.challenge4.backend.data.entity;

import com.rizky.challenge4.backend.data.SeatsEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seats")
public class Seats {

    @EmbeddedId
    private SeatsNumberID seatNumber;

    @Column(name = "seats")
    private SeatsEnum seatsRow;

}

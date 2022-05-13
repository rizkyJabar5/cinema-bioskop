package com.rizky.challenge4.backend.model.entity;

import com.rizky.challenge4.backend.model.enums.SeatsRowEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SeatsNumberID implements Serializable {

    private static final long serialVersionUID = 1292590841743792211L;

    @Column(name = "seats_number")
    private Long seatsNumber;

    @Column(name = "seats_row")
    @Enumerated(EnumType.STRING)
    private SeatsRowEnum seatsRow;

    @Override
    public int hashCode() {
        return Objects.hash(seatsNumber, seatsRow);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        SeatsNumberID seatsNumberID = (SeatsNumberID) obj;
        return seatsNumber.equals(seatsNumberID.seatsNumber) && seatsRow.equals(seatsNumberID.seatsRow);
    }

    @Override
    public String toString() {
        return "SeatsNumberID{" +
                "seatsNumber=" + seatsNumber +
                ", seatsRow=" + seatsRow +
                '}';
    }
}

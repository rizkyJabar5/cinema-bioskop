package com.rizky.challenge4.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SeatsNumberID implements Serializable {

    private static final long serialVersionUID = 1292590841743792211L;

    @Column(name = "seats_id")
    private Long seatsID;

    @Column(name = "seats_number")
    private Integer seatsNumber;

    @Override
    public int hashCode() {
        return Objects.hash(seatsID, seatsNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        SeatsNumberID seatsNumberID = (SeatsNumberID) obj;
        return seatsID.equals(seatsNumberID.seatsID) && seatsNumber.equals(seatsNumberID.seatsNumber);
    }

    @Override
    public String toString() {
        return "SeatsNumberID{" +
                "seatsID=" + seatsID +
                ", seatsNumber=" + seatsNumber +
                '}';
    }
}

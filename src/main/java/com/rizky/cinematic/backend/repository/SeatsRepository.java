package com.rizky.cinematic.backend.repository;

import com.rizky.cinematic.backend.model.entity.Seats;
import com.rizky.cinematic.backend.model.enums.SeatsRowEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatsRepository extends JpaRepository<Seats, Long> {

    @Query("select s from Seats s where s.seatsNumberId.seatsNumber = ?1 and s.seatsNumberId.seatsRow = ?2")
    Seats findSeatsBySeatsNumberId(Long seatsNumber, SeatsRowEnum seatsRow);

}

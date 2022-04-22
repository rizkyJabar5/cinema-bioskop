package com.rizky.challenge4.backend.repository;

import com.rizky.challenge4.backend.model.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatsRepository extends JpaRepository<Seats, Long> {

}
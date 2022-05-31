package com.rizky.cinematic.backend.repository;

import com.rizky.cinematic.backend.model.entity.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SchedulesRepository extends JpaRepository<Schedules, Long> {

    @Query("select s from Schedules s where s.showDate = ?1")
    List<Schedules> findAllByShowDateIs(Date showDate);

    @Query(value = "select s.* from films f join schedules s on f.film_id = s.film_id where f.film_id = ?1",
            nativeQuery = true)
    List<Schedules> findFilmSchedule(Long filmId);
}

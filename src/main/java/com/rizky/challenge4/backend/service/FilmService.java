package com.rizky.challenge4.backend.service;

import com.rizky.challenge4.backend.data.dto.ScheduleFilmDTO;
import com.rizky.challenge4.backend.data.entity.Films;
import com.rizky.challenge4.backend.data.entity.Schedules;

import java.util.List;
import java.util.Optional;

public interface FilmService {

    void addFilm(Films film);

    void addManyFilms(List<Films> film);

    Films updateFilm(Films film);

    Films getFilmById(long id);

    String deleteFilmById(long id);

    List<Films> findAllFilms();

    List<Films> findFilmByOnShow();

    Optional<ScheduleFilmDTO> showFilmOnSchedule(Long id);

    List<ScheduleFilmDTO> showAllSchedule();

    void addSchedule(Schedules schedules);
}

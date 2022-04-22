package com.rizky.challenge4.backend.service;

import com.rizky.challenge4.backend.model.dto.FilmDto;
import com.rizky.challenge4.backend.model.dto.ScheduleDto;
import com.rizky.challenge4.backend.model.entity.Films;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface FilmService {

    Films addFilm(FilmDto dto);

    void addManyFilms(List<Films> film);

    Films updateFilm(Films film);

    Films getFilmById(long id);

    Films getFilmByCodeName(String codeName);

    void deleteFilmById(long id);

    List<Films> findAllFilms();

    List<Films> findFilmByOnShow();

    Optional<ScheduleDto> showFilmOnSchedule(Long id);

    List<ScheduleDto> showAllSchedule();

    void addSchedule(ScheduleDto schedules, FilmDto filmDto) throws ParseException;
}

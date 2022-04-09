package com.rizky.challenge4.backend.service;

import com.rizky.challenge4.backend.data.dto.ScheduleFilmDTO;
import com.rizky.challenge4.backend.data.entity.Films;

import java.util.List;

public interface FilmService {

    Films addFilm(Films film);

    List<Films> addManyFilms(List<Films> film);

    Films updateFilm(Films film);

    Films getFilmById(long id);

    String deleteFilmById(long id);

    List<Films> findAllFilms();

    List<Films> findFilmByOnShow();

    List<ScheduleFilmDTO> showAllFilmOnShow(Long id);
}

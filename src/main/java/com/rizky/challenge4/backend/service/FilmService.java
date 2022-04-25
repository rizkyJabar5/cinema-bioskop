package com.rizky.challenge4.backend.service;

import com.rizky.challenge4.backend.model.dto.FilmDto;
import com.rizky.challenge4.backend.model.entity.Films;

import java.util.List;

public interface FilmService {

    void addFilm(FilmDto dto);

    void addManyFilms(List<Films> film);

    Films updateFilm(Films film);

    Films getFilmById(long id);

    Films getFilmByCodeName(String codeName);

    void deleteFilmById(long id);

    List<Films> findAllFilms();

    List<Films> findFilmByOnShow();

}

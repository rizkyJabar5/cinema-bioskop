package com.rizky.cinematic.backend.controller;

import com.rizky.cinematic.backend.model.dto.FilmDto;
import com.rizky.cinematic.backend.model.entity.Films;
import com.rizky.cinematic.backend.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith({MockitoExtension.class})
class FilmControllerTest {

    @Mock
    private FilmService filmService;
    private FilmController filmController;

    List<Films> filmsList;

    Films film;

    @BeforeEach
    void setUp() {
        filmController = new FilmController(filmService);

        filmsList = Arrays.asList(
                new Films(1L, "AVG", "Avengers", "Peperangan antar super hero", true),
                new Films(2L, "MPN", "Master Perang Negeri", "Jagoan negeri", false),
                new Films(3L, "PK", "Pocong Kesurupan", "HAntu yang kesurupan", true),
                new Films(4L, "MLG", "Malin Kundang Go Internasional", "Anak durhaka yang go internasional", false)
        );

        film = new Films(
                1L, "AVG", "Avengers", "Peperangan antar super hero", true
        );
    }

    @Test
    @DisplayName("Test to add many film with list")
    void addManyFilms() {

        assertThat(new ResponseEntity<>(filmsList.toString(), HttpStatus.ACCEPTED))
                .usingRecursiveComparison().isEqualTo(filmController.addManyFilms(filmsList));

    }

    @Test
    @DisplayName("Test GET film by id")
    void getFilmById() {

        long filmId = 1L;

        filmController.getFilmById(filmId);

        Mockito.verify(filmService).getFilmById(filmId);


    }

    @Test
    @DisplayName("Test to add new film")
    void testAddNewFilm() {

        FilmDto dto = new FilmDto(
                film.getFilmID(),
                film.getCodeFilm(),
                film.getTitle(),
                film.getDescription(),
                film.isOnShow());

        filmController.addFilm(dto);

        Mockito.verify(filmService, Mockito.times(1)).addFilm(dto);

        assertThat(new ResponseEntity<>(dto.toString(), HttpStatus.ACCEPTED))
                .usingRecursiveComparison().isEqualTo(filmController.addFilm(dto));

    }

    @Test
    @DisplayName("Test to get film on show")
    void testGetFilmOnShow() {

        filmController.getFilmOnShow();

        Mockito.verify(filmService, Mockito.times(1)).findFilmByOnShow();

    }

    @Test
    @DisplayName("Test to update film for existing film")
    void updateFilm(){

        Films updateFilm = Mockito.spy(film);
        updateFilm.setTitle("Avengers End Game");

        filmController.updateFilmID(updateFilm);

        Mockito.verify(filmService).updateFilm(updateFilm);
    }

    @Test
    @DisplayName("Test to delete film by id")
    void deleteFilmById() {
        filmController.deleteFilmById(1L);
        Mockito.verify(filmService).deleteFilmById(1L);
    }
}
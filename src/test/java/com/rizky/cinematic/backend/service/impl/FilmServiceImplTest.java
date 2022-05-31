package com.rizky.cinematic.backend.service.impl;

import com.rizky.cinematic.backend.exceptions.NotFoundExceptions;
import com.rizky.cinematic.backend.model.dto.FilmDto;
import com.rizky.cinematic.backend.model.entity.Films;
import com.rizky.cinematic.backend.model.mapper.FilmDtoToModel;
import com.rizky.cinematic.backend.repository.FilmRepository;
import com.rizky.cinematic.backend.service.FilmService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceImplTest {

    @Mock
    private FilmRepository filmRepository;
    @Mock
    private FilmDtoToModel convertToModel;

    FilmService filmService;

    Films film;

    @BeforeEach
    void setUp() {
        filmService = new FilmServiceImpl(
                filmRepository,
                convertToModel
        );
        film = new Films(
                1L,
                "AVG",
                "Avengers",
                "Peperangan antar super hero",
                true
        );

    }

    @Test
    void addFilm() {

        FilmDto request = new FilmDto();
        request.setFilmId(1L);
        request.setCodeFilm("MPN");
        request.setTitle("Maling Pacar Nanang");
        request.setDescription("Sebuah film terobosan baru");
        request.setOnShow(true);

        Films movie= new Films(
                request.getFilmId(),
                request.getCodeFilm(),
                request.getTitle(),
                request.getDescription(),
                request.isOnShow() );

        when(filmRepository.save(convertToModel.createToModel(request)))
                .thenReturn(movie);
        filmService.addFilm(request);
        verify(filmRepository, times(1))
                .save(convertToModel.createToModel(request));

    }

    @Test
    void addManyFilms() {

        List<Films> filmsList = Arrays.asList(
                new Films(1L, "AVG", "Avengers", "Peperangan antar super hero", true),
                new Films(2L, "MPN", "Master Perang Negeri", "Jagoan negeri", false),
                new Films(3L, "PK", "Pocong Kesurupan", "HAntu yang kesurupan", true),
                new Films(4L, "MLG", "Malin Kundang Go Internasional", "Anak durhaka yang go internasional", false)
        );

        filmService.addManyFilms(filmsList);

        verify(filmRepository, times(1))
                .saveAll(filmsList);

    }

    @Test
    void updateFilm() {

        Films update = Mockito.spy(film);


        update.setTitle(film.getTitle());
        update.setCodeFilm(film.getCodeFilm());
        update.setDescription(film.getDescription());
        update.setOnShow(false);

        when(filmRepository.findById(1L)).thenReturn(Optional.of(update));

        filmService.updateFilm(update);
        verify(filmRepository, times(1)).save(update);
    }

    @Test
    void checkFilmExist_Should_Throw() {

        Films update = spy(film);

        FilmDto request = new FilmDto();
        request.setFilmId(1L);
        request.setCodeFilm("AVG");
        request.setTitle("Maling Pacar Nanang");
        request.setDescription("Sebuah film terobosan baru");
        request.setOnShow(true);

        when(filmRepository.findFilmsByCodeFilm(request.getCodeFilm()))
                .thenReturn(Optional.of(film));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filmService.addFilm(request));
        verify(filmRepository, times(1))
                .findFilmsByCodeFilm(update.getCodeFilm());
    }

    @Test
    void getFilmById() {

        long id = 1L;
        when(filmRepository.findById(id)).thenReturn(Optional.ofNullable(film));

        filmService.getFilmById(film.getFilmID());
        verify(filmRepository).findById(id);
    }

    @Test
    void film_If_ID_NotFound_testIsCorrect() {

        when(filmRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundExceptions.class,
                () -> filmService.getFilmById(10L));

        verify(filmRepository).findById(10L);

    }

    @Test
    void getFilmByCodeName() {

        String code = "MPN";
        when(filmRepository.findFilmsByCodeFilm(code))
                .thenReturn(Optional.ofNullable(film));
        filmService.getFilmByCodeName(code);
        verify(filmRepository).findFilmsByCodeFilm(code);
    }

    @Test
    void notFoundCodeName_should_throw() {

        Films movie = spy(film);

        String ngawur = "Yahhhhhhhh";
        Mockito.when(filmRepository.findFilmsByCodeFilm(ngawur))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class,
                () -> filmService.getFilmByCodeName(ngawur));

        verify(filmRepository, times(1)).findFilmsByCodeFilm(ngawur);

    }

    @Test
    void deleteFilmById() {

        long id = 1L;
        filmService.deleteFilmById(id);
        verify(filmRepository).deleteById(film.getFilmID());
    }

    @Test
    void findAllFilms() {

        List<Films> filmsList = Arrays.asList(
                new Films(1L, "AVG", "Avengers", "Peperangan antar super hero", true),
                new Films(2L, "MPN", "Master Perang Negeri", "Jagoan negeri", false),
                new Films(3L, "PK", "Pocong Kesurupan", "HAntu yang kesurupan", true),
                new Films(4L, "MLG", "Malin Kundang Go Internasional", "Anak durhaka yang go internasional", false)
        );

        when(filmRepository.findAll()).thenReturn(filmsList);
        filmService.findAllFilms();
        Mockito.verify(filmRepository).findAll();


    }

    @Test
    void findFilmByOnShow() {

        List<Films> filmsList = Arrays.asList(
                new Films(1L, "AVG", "Avengers", "Peperangan antar super hero", true),
                new Films(2L, "MPN", "Master Perang Negeri", "Jagoan negeri", false),
                new Films(3L, "PK", "Pocong Kesurupan", "HAntu yang kesurupan", true),
                new Films(4L, "MLG", "Malin Kundang Go Internasional", "Anak durhaka yang go internasional", false)
        );

        when(filmRepository.findFilmsByOnShow()).thenReturn(filmsList);
        filmService.findFilmByOnShow();
        verify(filmRepository).findFilmsByOnShow();

    }
}
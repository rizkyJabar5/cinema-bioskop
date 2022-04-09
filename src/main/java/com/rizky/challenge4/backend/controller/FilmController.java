package com.rizky.challenge4.backend.controller;

import com.rizky.challenge4.backend.data.dto.ScheduleFilmDTO;
import com.rizky.challenge4.backend.data.entity.Films;
import com.rizky.challenge4.backend.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Controller
public class FilmController {

    @Autowired
    private FilmService filmService;

    @PostMapping("/films")
    public String addFilm(@RequestBody Films film) {
        filmService.addFilm(film);
        return film.toString();
    }

    @PostMapping("/many-film")
    public String addManyFilms(@RequestBody List<Films> film) {
        filmService.addManyFilms(film);
        return film.toString();
    }

    @GetMapping("/films")
    public ResponseEntity<List<Films>> getAllFilms(String title) {
        return ResponseEntity.ok().body(filmService.findAllFilms());
    }

    @GetMapping("/film/{id}")
    public Films getFilmById(@PathVariable("id") long id) {
        return filmService.getFilmById(id);
    }

    @GetMapping("/filmshow")
    public List<Films> getFilmOnShow() {
        return filmService.findFilmByOnShow();
    }

    @PutMapping("/update-film")
    public Films updateFilmID(@RequestBody Films film) {
        return filmService.updateFilm(film);
    }

    @DeleteMapping("/delete-film/{id}")
    public String deleteFilmById(@PathVariable("id") long id) {
        return filmService.deleteFilmById(id);
    }

    //    ----------------Schedule----------------
    @GetMapping("/schedule/{id}")
    public List<ScheduleFilmDTO> getScheduleFilm(@PathVariable("id") Long id) {
                return filmService.showAllFilmOnShow(id);
    }
}
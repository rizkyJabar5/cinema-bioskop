package com.rizky.challenge4.backend.controller;

import com.rizky.challenge4.backend.model.dto.FilmDto;
import com.rizky.challenge4.backend.model.dto.ScheduleDto;
import com.rizky.challenge4.backend.model.entity.Films;
import com.rizky.challenge4.backend.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Tag(name="Films Controller", description = "API for handling transaction Film and Schedule CRUD operation entity in the Cinematic database.")
@RestController
@RequestMapping("/api/v1/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Operation(summary = "This method function to add film detailing.")
    @PostMapping("/add")
    public ResponseEntity<String> addFilm(@RequestBody FilmDto dto) {
        filmService.addFilm(dto);
        return ResponseEntity.status(201).body(dto.toString());
    }

    @Operation(summary = "This method function to add many film detailing with array object.")
    @PostMapping("/add-films")
    public ResponseEntity<String> addManyFilms(@RequestBody List<Films> film) {
        filmService.addManyFilms(film);
        return ResponseEntity.status(201).body(film.toString());
    }

    @Operation(summary = "Get or show all the data entity from the database.")
    @GetMapping("/showall")
    public ResponseEntity<List<Films>> getAllFilms() {
        return ResponseEntity.status(202).body(filmService.findAllFilms());
    }

    @Operation(summary = "Get or search the data by id from the entity.")
    @GetMapping("/{id}")
    public ResponseEntity<Films> getFilmById(@PathVariable("id") long id) {
        return ResponseEntity.status(202).body(filmService.getFilmById(id));
    }

    @Operation(summary = "Get or search movie is on show.")
    @GetMapping("/onshow")
    public ResponseEntity<List<Films>> getFilmOnShow() {
        return ResponseEntity.status(202).body(filmService.findFilmByOnShow());
    }

    @Operation(summary = "This method function to edit film detail.")
    @PutMapping("/update")
    public ResponseEntity<Films> updateFilmID(@RequestBody Films film) {
        return ResponseEntity.ok().body(filmService.updateFilm(film));
    }

    @Operation(summary = "Delete movie by calling id")
    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteFilmById(@PathVariable("id") long id) {
        filmService.deleteFilmById(id);
        return ResponseEntity.accepted().body("Delete film " + id + "has successfully.");

    }

    //    ----------------Schedule----------------
    @Operation(summary = "Get or search the data by id from the entity.")
    @GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleDto> getScheduleFilm(@PathVariable("id") Long id) {
        return ResponseEntity.status(203).body(filmService.showFilmOnSchedule(id).get());
    }

    @Operation(summary = "Get or search all entity data of field from the database.")
    @GetMapping("/list-schedules")
    public ResponseEntity<List<ScheduleDto>> showAllSchedule(){
        return ResponseEntity.status(203).body(filmService.showAllSchedule());
    }

    @Operation(summary = "This method function to add schedule if film is present on the database.")
    @PostMapping("/schedule/new")
    public ResponseEntity<String> addNewSchedule(@RequestBody ScheduleDto sch, FilmDto film) throws ParseException {
        filmService.addSchedule(sch, film);
        return ResponseEntity.ok().body(sch.toString());
    }
}
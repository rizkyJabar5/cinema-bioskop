package com.rizky.cinematic.backend.controller;

import com.rizky.cinematic.backend.model.dto.FilmDto;
import com.rizky.cinematic.backend.model.entity.Films;
import com.rizky.cinematic.backend.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Films Controller", description = "API for handling transaction Film CRUD operation entity in the Cinematic database.")
@RestController
@RequestMapping("/api/v1/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @Operation(summary = "This method function to add film detailing.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Add film to store the database ",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "FilmDto{" +
                                    "filmId= 1," +
                                    "codeFilm:MPN," +
                                    "title:Malaikat Pencabut Nyawa," +
                                    "description:Malaikat yang sering ditakuti oleh kebanyakan manusia," +
                                    "onShow:true" +
                                    '}'))})})
    @PostMapping("/admin/add")
    public ResponseEntity<String> addFilm(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Created film object", required = true)// Swagger Custom response body
            @RequestBody FilmDto dto) {
        filmService.addFilm(dto);
        return ResponseEntity.accepted().body(dto.toString());
    }

    @Operation(summary = "This method function to add many film detailing with array object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Add film array object to store the database ",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "FilmDto{" +
                                    "filmId= 1," +
                                    "codeFilm:MPN," +
                                    "title:Malaikat Pencabut Nyawa," +
                                    "description:Malaikat yang sering ditakuti oleh kebanyakan manusia," +
                                    "onShow:true" +
                                    '}'))})})
    @PostMapping("/admin/add-many-films")
    public ResponseEntity<String> addManyFilms(@RequestBody List<Films> film) {
        filmService.addManyFilms(film);
        return ResponseEntity.accepted().body(film.toString());
    }

    @Operation(summary = "Get or show all the data entity from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Get all film on the database",
                    content = {@Content(
                            mediaType = "application/json",
                    schema = @Schema(example = "[{" +
                            "filmId= 1," +
                            "codeFilm:MPN," +
                            "title:Malaikat Pencabut Nyawa," +
                            "description:Malaikat yang sering ditakuti oleh kebanyakan manusia," +
                            "onShow:true" +
                            '}' +
                            "{" +
                            "filmId= 2," +
                            "codeFilm:ASB," +
                            "title:Arini Suka Brondong," +
                            "description: Sekumpulan cewek yang sedang nongkrong dicafe," +
                            "onShow:true" +
                            '}'))
                    })
    })
    @GetMapping("/public/search/showall")
    public ResponseEntity<List<Films>> getAllFilms() {
        return ResponseEntity.accepted().body(filmService.findAllFilms());
    }

    @Operation(summary = "Get or search the data by id from the entity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Get film by id on the database",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "FilmDto{" +
                                    "filmId= 1," +
                                    "codeFilm:MPN," +
                                    "title:Malaikat Pencabut Nyawa," +
                                    "description:Malaikat yang sering ditakuti oleh kebanyakan manusia," +
                                    "onShow:true" +
                                    '}'))
                    })
    })
    @GetMapping("/public/search/{id}")
    public ResponseEntity<Films> getFilmById(
            @Parameter(description = "add id for get the film")
            @PathVariable("id") long id) {
        return ResponseEntity.accepted().body(filmService.getFilmById(id));
    }

    @Operation(summary = "Get or search movie is on show.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Get film by onshow parameter on the database",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "FilmDto{" +
                                    "filmId= 1," +
                                    "codeFilm:MPN," +
                                    "title:Malaikat Pencabut Nyawa," +
                                    "description:Malaikat yang sering ditakuti oleh kebanyakan manusia," +
                                    "onShow:true" +
                                    '}'))
                    })
    })
    @GetMapping("/public/search/onshow")
    public ResponseEntity<List<Films>> getFilmOnShow() {
        return ResponseEntity.accepted().body(filmService.findFilmByOnShow());
    }

    @Operation(summary = "This method function to edit film detail.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update film on the database with match by id",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "FilmDto{" +
                                    "filmId= 1," +
                                    "codeFilm:MPN," +
                                    "title:Malaikat Pencabut Nyawa," +
                                    "description:Malaikat yang sering ditakuti oleh kebanyakan manusia," +
                                    "onShow:true" +
                                    '}'))
                    })
    })
    @PutMapping("/admin/update")
    public ResponseEntity<Films> updateFilmID(@RequestBody Films film) {
        return ResponseEntity.ok().body(filmService.updateFilm(film));
    }

    @Operation(summary = "Delete movie by calling id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Delete film by id with parameter",
                    content = {@Content(
                            schema = @Schema(example = "Delete film id 1 has successfully."))
                    })
    })
    @DeleteMapping("/admin/del/{id}")
    public ResponseEntity<String> deleteFilmById(
            @Parameter(description = "add id for delete the film")
            @PathVariable("id") long id) {
        filmService.deleteFilmById(id);
        return ResponseEntity.accepted().body("Delete film " + id + "has successfully.");

    }

}
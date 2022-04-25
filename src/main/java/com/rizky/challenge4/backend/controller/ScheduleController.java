package com.rizky.challenge4.backend.controller;

import com.rizky.challenge4.backend.error.NotFoundExceptions;
import com.rizky.challenge4.backend.model.dto.ScheduleDto;
import com.rizky.challenge4.backend.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Schedule Controller", description = "API for handling transaction schdeule CRUD operation entity in the Cinematic database.")
@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    //    ----------------GET MAPPING WITH ID----------------
    @Operation(summary = "Get or search the data by id from the entity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "203", description = "Get schedule by id ")
    })
    @GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleDto> getScheduleFilm(
            @PathVariable("id") Long id) {
        return ResponseEntity.status(203)
                .body(service.showFilmOnSchedule(id)
                        .orElseThrow(() -> new NotFoundExceptions("Schedule with id " + id + " not found")));
    }

    //    ----------------GET MAPPING LIST ALL SCHEDULE----------------
    @Operation(summary = "Get or search all entity data of field from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "203", description = "Get all Schedule store on the database")
    })
    @GetMapping("/showall")
    public ResponseEntity<List<ScheduleDto>> showAllSchedule() {
        return ResponseEntity.status(203).body(service.showAllSchedule());
    }

    //    ---------POST MAPPING----------------
    @Operation(summary = "This method function to add schedule if film is present on the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Add film to store the database ",
                    content = {@Content(
                            schema = @Schema(implementation = ScheduleDto.class),
                            mediaType = "application/json")})})
    @PostMapping("/new")
    public ResponseEntity<String> addNewSchedule(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Created schedule object",
                    content = @Content(
                            schema = @Schema(implementation = ScheduleDto.class)
                    ))

            @Valid @RequestBody ScheduleDto sch) {
        service.addSchedule(sch);
        return ResponseEntity.ok().body(sch.toString());
    }
}

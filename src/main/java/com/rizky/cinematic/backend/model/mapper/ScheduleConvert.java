package com.rizky.cinematic.backend.model.mapper;

import com.rizky.cinematic.backend.exceptions.NotFoundExceptions;
import com.rizky.cinematic.backend.model.dto.FilmDto;
import com.rizky.cinematic.backend.model.dto.ScheduleDto;
import com.rizky.cinematic.backend.model.entity.Films;
import com.rizky.cinematic.backend.model.entity.Schedules;
import com.rizky.cinematic.backend.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConvert {

    private final FilmService filmService;
    private final FilmDtoToModel createFilm;
    @Autowired
    public ScheduleConvert(FilmService filmService, FilmDtoToModel createFilm) {
        this.filmService = filmService;
        this.createFilm = createFilm;
    }

    public ScheduleDto getScheduleFilms(Schedules s) {

        ScheduleDto schedules = new ScheduleDto();

        schedules.setSchedulesId(s.getSchedulesId());
        schedules.setShowDate(s.getShowDate());
        schedules.setStartTime(s.getStartTime());
        schedules.setEndTime(s.getEndTime());
        schedules.setPrice(s.getPrice());
        schedules.setTitleFilm(s.getFilm().getTitle());
        if (!s.getFilm().isOnShow()) {
            schedules.setOnShow("Belum Tayang");
        } else {
            schedules.setOnShow("Sudah Tayang");
        }
        return schedules;
    }

    public Schedules addNewSchedule(ScheduleDto dto) {

        Schedules entity = new Schedules();
        entity.setSchedulesId(dto.getSchedulesId());
        Films filmID = filmService.getFilmById(dto.getFilm().getFilmID());
        if (filmID == null) {
            createFilm.createToModel(new FilmDto());
            throw new NotFoundExceptions("Film id " + dto.getFilm().getFilmID() + " not found. Please create one");
        } else {
            entity.setFilm(dto.getFilm());
        }
        entity.setShowDate(dto.getShowDate());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setPrice(dto.getPrice());
        return entity;
    }

}

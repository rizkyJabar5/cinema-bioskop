package com.rizky.cinematic.backend.controller;

import com.rizky.cinematic.backend.model.entity.Films;
import com.rizky.cinematic.backend.model.entity.Schedules;
import com.rizky.cinematic.backend.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
class ScheduleControllerTest {

    @Mock
    private ScheduleService service;

    Schedules schedule;
    ScheduleController controller;

    @BeforeEach
    void setUp() {
        Films film = new Films(
                1L, "AVG", "Avengers", "Peperangan antar super hero", true
        );

        schedule = new Schedules();
        schedule.setSchedulesId(1L);
        schedule.setStartTime(new Date(System.currentTimeMillis()));
        schedule.setEndTime(new Date(System.currentTimeMillis() + 7200));
        schedule.setFilm(film);
        schedule.setShowDate(new Date(System.currentTimeMillis()));
        schedule.setPrice(20_000f);

    }

    @Test
    @Disabled
    void getScheduleFilm() {

        long id = 1L;
        Schedules sch = Mockito.spy(schedule);
        Mockito.doReturn(sch).when(service).findFilmOnSchedule(sch.getSchedulesId());

        controller.getScheduleFilm(id);
        Mockito.verify(service).findFilmOnSchedule(id);

    }

    @Test
    void showAllSchedule() {
    }

    @Test
    void addNewSchedule() {
    }
}
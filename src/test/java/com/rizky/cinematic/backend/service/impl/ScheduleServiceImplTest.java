package com.rizky.cinematic.backend.service.impl;

import com.rizky.cinematic.backend.model.entity.Films;
import com.rizky.cinematic.backend.model.entity.Schedules;
import com.rizky.cinematic.backend.model.mapper.ScheduleConvert;
import com.rizky.cinematic.backend.repository.SchedulesRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ScheduleServiceImplTest{

    @Autowired
    private SchedulesRepository repository;

    @Autowired
    private ScheduleConvert scheduleConvert;
//
//    ScheduleService scheduleService;
//
//    @BeforeEach
//    void setUp(){
//        scheduleService = new ScheduleServiceImpl(
//                repository,
//                scheduleConvert
//        );
//    }

    @Test
    @DisplayName("Add One Schedules")
    void addSchedule() {

//


        Schedules schedules = new Schedules(
                1L, new Films(), new Date(), new Date(), new Date(), 20_000f
        );

        Schedules save = repository.save(schedules);

        assertThat(save).isNotNull();
        assertThat(repository.count()).isEqualTo(1);

    }

    @Test
    void findFilmOnSchedule() {
    }

    @Test
    void findScheduleById() {
    }

    @Test
    void findAllSchedule() {
    }
}
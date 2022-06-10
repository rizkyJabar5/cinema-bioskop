package com.rizky.cinematic.backend.service;

import com.rizky.cinematic.app.HasLogger;
import com.rizky.cinematic.backend.model.dto.ScheduleDto;
import com.rizky.cinematic.backend.model.entity.Schedules;

import java.util.List;
import java.util.Optional;

public interface ScheduleService extends HasLogger {

    Optional<ScheduleDto> findFilmOnSchedule(Long id);

    Schedules findScheduleById(Long id);

    List<ScheduleDto> findAllSchedule();

    void addSchedule(ScheduleDto schedules);
}

package com.rizky.challenge4.backend.service;

import com.rizky.challenge4.backend.model.dto.ScheduleDto;
import com.rizky.challenge4.backend.model.entity.Schedules;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    Optional<ScheduleDto> findFilmOnSchedule(Long id);

    Schedules findScheduleById(Long id);

    List<ScheduleDto> findAllSchedule();

    void addSchedule(ScheduleDto schedules);
}

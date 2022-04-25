package com.rizky.challenge4.backend.service;

import com.rizky.challenge4.backend.model.dto.ScheduleDto;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    Optional<ScheduleDto> showFilmOnSchedule(Long id);

    List<ScheduleDto> showAllSchedule();

    void addSchedule(ScheduleDto schedules);
}

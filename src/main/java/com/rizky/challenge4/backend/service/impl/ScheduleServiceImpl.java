package com.rizky.challenge4.backend.service.impl;

import com.rizky.challenge4.backend.model.dto.ScheduleDto;
import com.rizky.challenge4.backend.model.mapper.ScheduleConvert;
import com.rizky.challenge4.backend.repository.SchedulesRepository;
import com.rizky.challenge4.backend.service.FilmService;
import com.rizky.challenge4.backend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final SchedulesRepository repository;
    private final ScheduleConvert scheduleConvert;
    private final FilmService filmService;

    @Autowired
    public ScheduleServiceImpl(SchedulesRepository repository, ScheduleConvert scheduleConvert, FilmService filmService) {
        this.repository = repository;
        this.scheduleConvert = scheduleConvert;
        this.filmService = filmService;
    }

    @Override
    public void addSchedule(ScheduleDto dto) {
        repository.save(scheduleConvert.addNewSchedule(dto));
    }

    @Override
    public Optional<ScheduleDto> showFilmOnSchedule(Long id) {
        return repository.findFilmSchedule(id)
                .stream()
                .map(scheduleConvert::getScheduleFilms)
                .findFirst();
    }

    @Override
    public List<ScheduleDto> showAllSchedule() {
        return repository.findAll()
                .stream()
                .map(scheduleConvert::getScheduleFilms)
                .collect(Collectors.toList());
    }

}

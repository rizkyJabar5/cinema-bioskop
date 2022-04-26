package com.rizky.challenge4.backend.service.impl;

import com.rizky.challenge4.backend.error.NotFoundExceptions;
import com.rizky.challenge4.backend.model.dto.ScheduleDto;
import com.rizky.challenge4.backend.model.entity.Schedules;
import com.rizky.challenge4.backend.model.mapper.ScheduleConvert;
import com.rizky.challenge4.backend.repository.SchedulesRepository;
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

    @Autowired
    public ScheduleServiceImpl(SchedulesRepository repository, ScheduleConvert scheduleConvert) {
        this.repository = repository;
        this.scheduleConvert = scheduleConvert;
    }

    @Override
    public void addSchedule(ScheduleDto dto) {
        repository.save(scheduleConvert.addNewSchedule(dto));
    }

    @Override
    public Optional<ScheduleDto> findFilmOnSchedule(Long id) {
        return repository.findFilmSchedule(id)
                .stream()
                .map(scheduleConvert::getScheduleFilms)
                .findFirst();
    }

    @Override
    public Schedules findScheduleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->  new NotFoundExceptions("Schedule " + id + " not found"));
    }

    @Override
    public List<ScheduleDto> findAllSchedule() {
        return repository.findAll()
                .stream()
                .map(scheduleConvert::getScheduleFilms)
                .collect(Collectors.toList());
    }

}

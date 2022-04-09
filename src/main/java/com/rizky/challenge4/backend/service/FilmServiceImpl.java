package com.rizky.challenge4.backend.service;

import com.rizky.challenge4.backend.data.dto.ScheduleFilmDTO;
import com.rizky.challenge4.backend.data.entity.Films;
import com.rizky.challenge4.backend.data.mapper.ScheduleFilmConvert;
import com.rizky.challenge4.backend.error.NotFoundExceptions;
import com.rizky.challenge4.backend.repository.FilmRepository;
import com.rizky.challenge4.backend.repository.SchedulesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private final FilmRepository filmRepository;
    @Autowired
    private final SchedulesRepository schedulesRepository;
    @Autowired
    private ScheduleFilmConvert convert;

    public FilmServiceImpl(FilmRepository filmRepository, SchedulesRepository schedulesRepository) {
        this.filmRepository = filmRepository;
        this.schedulesRepository = schedulesRepository;
    }

    @Override
    public Films addFilm(Films film) {
        log.info("Film {} has been successfully saved to the database", film.getTitle());
        return filmRepository.save(film);
    }

    @Override
    public List<Films> addManyFilms(List<Films> film) {
        log.info("Films has been successfully saved to the database");
        return filmRepository.saveAll(film);
    }

    @Transient
    @Override
    public Films updateFilm(Films film) {
        log.info("Film {} has been successfully to update", film.getTitle());
        Films updateFilm = filmRepository.findById(film.getFilmID())
                .orElseThrow(() -> new NotFoundExceptions("Film not found"));
        Objects.requireNonNull(updateFilm).setTitle(film.getTitle());
        updateFilm.setDescription(film.getDescription());
        updateFilm.setOnShow(film.isOnShow());
        return filmRepository.save(updateFilm);
    }

    @Override
    public Films getFilmById(long id) {
        return filmRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundExceptions("Film: " + id + " yang kamu cari tidak ada"));
    }

    @Override
    public String deleteFilmById(long id) {
        filmRepository.deleteById(id);
        return "Delete " + id + " succesfully";
    }

    @Override
    public List<Films> findAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public List<Films> findFilmByOnShow() {
        return filmRepository.findFilmsByOnShow();
    }

    @Override
    public List<ScheduleFilmDTO> showAllFilmOnShow(Long id) {
        return schedulesRepository.findFilmSchedule(id)
                .stream()
                .map(convert::convertScheduleFilmToDto)
                .collect(Collectors.toList());
    }

//    ScheduleFilmDTO convertScheduleFilmToDto(Schedules s){
//        ScheduleFilmDTO schedules =  new ScheduleFilmDTO();
//        schedules.setSchedulesID(s.getSchedulesID());
//        schedules.setShowDate(s.getShowDate());
//        schedules.setStartTime(s.getStartTime());
//        schedules.setEndTime(s.getEndTime());
//        schedules.setPrice(s.getPrice());
//        schedules.setTitleFilm(s.getFilm().getTitle());
//
//        return schedules;
//    }
}

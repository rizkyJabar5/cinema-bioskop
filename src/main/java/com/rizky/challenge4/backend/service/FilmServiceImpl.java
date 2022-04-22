package com.rizky.challenge4.backend.service;

import com.rizky.challenge4.backend.error.NotFoundExceptions;
import com.rizky.challenge4.backend.model.dto.FilmDto;
import com.rizky.challenge4.backend.model.dto.ScheduleDto;
import com.rizky.challenge4.backend.model.entity.Films;
import com.rizky.challenge4.backend.model.entity.Schedules;
import com.rizky.challenge4.backend.model.mapper.FilmDtoToModel;
import com.rizky.challenge4.backend.model.mapper.ScheduleConvert;
import com.rizky.challenge4.backend.repository.FilmRepository;
import com.rizky.challenge4.backend.repository.SchedulesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final SchedulesRepository schRepository;
    private final ScheduleConvert convertToDto;
    private final FilmDtoToModel convertToModel;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository, SchedulesRepository schRepository, ScheduleConvert convertToDto, FilmDtoToModel convertToModel) {
        this.filmRepository = filmRepository;
        this.schRepository = schRepository;
        this.convertToDto = convertToDto;
        this.convertToModel = convertToModel;
    }

    @Override
    public Films addFilm(FilmDto dto) {
        log.info("Film {} has been successfully saved to the database", dto.getTitle());
        return filmRepository.save(convertToModel.createToModel(dto));
    }

    @Override
    public void addManyFilms(List<Films> film) {
        log.info("Films has been successfully saved to the database");
        filmRepository.saveAll(film);
    }

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
    public Films getFilmByCodeName(String codeName) {
        return filmRepository.findFilmsByCodeFilm(codeName);
    }

    @Override
    public void deleteFilmById(long id) {
        filmRepository.deleteById(id);
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
    public Optional<ScheduleDto> showFilmOnSchedule(Long id) {
        return schRepository.findFilmSchedule(id)
                .stream()
                .map(convertToDto::getConvertToDto)
                .findFirst();
    }

    @Override
    public List<ScheduleDto> showAllSchedule() {
        return schRepository.findAll()
                .stream()
                .map(convertToDto::getConvertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addSchedule(ScheduleDto dto, FilmDto filmDto) throws ParseException {

        Schedules entity = new Schedules();
        entity.setShowDate(dto.getDateConverted("Asia/Jakarta"));
        entity.setPrice(dto.getPrice());
        entity.setStartTime(dto.getStartTimeConverted("Asia/Jakarta"));
        entity.setEndTime(dto.getEndTimeConverted("Asia/Jarkata"));
        Films films = getFilmByCodeName(dto.getCodeTitleFilm());
        if (films != null) {
            entity.setFilm(films);
        } else {
            addFilm(filmDto);
            entity.setFilm(getFilmByCodeName(dto.getCodeTitleFilm()));
        }
        schRepository.save(entity);
    }

}

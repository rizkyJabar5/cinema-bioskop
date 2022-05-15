package com.rizky.challenge4.backend.service.impl;

import com.rizky.challenge4.app.HasLogger;
import com.rizky.challenge4.backend.exceptions.NotFoundExceptions;
import com.rizky.challenge4.backend.model.dto.FilmDto;
import com.rizky.challenge4.backend.model.entity.Films;
import com.rizky.challenge4.backend.model.mapper.FilmDtoToModel;
import com.rizky.challenge4.backend.repository.FilmRepository;
import com.rizky.challenge4.backend.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FilmServiceImpl implements FilmService, HasLogger {

    private final FilmRepository filmRepository;
    private final FilmDtoToModel convertToModel;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository, FilmDtoToModel convertToModel) {
        this.filmRepository = filmRepository;
        this.convertToModel = convertToModel;
    }

    @Override
    public void addFilm(FilmDto dto) {

        Films existingFilm = filmRepository.findFilmsByCodeFilm(dto.getCodeFilm());

        if (existingFilm == null) {
            getLogger().info("Film {} has been successfully saved to the database", dto.getTitle());
            filmRepository.save(convertToModel.createToModel(dto));
        }

    }

    @Override
    public void addManyFilms(List<Films> film) {
        getLogger().info("Films has been successfully saved to the database");
        filmRepository.saveAll(film);
    }

    @Override
    public Films updateFilm(Films film) {
        getLogger().info("Film {} has been successfully to update", film.getTitle());
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
                        new NotFoundExceptions("Film: " + id + " yang kamu cari tidak ada"  ));
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

}

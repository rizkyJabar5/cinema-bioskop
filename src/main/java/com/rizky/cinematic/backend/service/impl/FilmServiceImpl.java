package com.rizky.cinematic.backend.service.impl;

import com.rizky.cinematic.app.HasLogger;
import com.rizky.cinematic.backend.exceptions.NotFoundExceptions;
import com.rizky.cinematic.backend.model.dto.FilmDto;
import com.rizky.cinematic.backend.model.entity.Films;
import com.rizky.cinematic.backend.model.mapper.FilmDtoToModel;
import com.rizky.cinematic.backend.repository.FilmRepository;
import com.rizky.cinematic.backend.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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

        checkFilmExist(dto.getCodeFilm());

        getLogger().info("Film {} has been successfully saved to the database", dto.getTitle());
        filmRepository.save(convertToModel.createToModel(dto));

    }

    private void checkFilmExist(String codeFilm) {
        boolean existingFilm = filmRepository.findFilmsByCodeFilm(codeFilm).isPresent();
        if (existingFilm) {
            getLogger().error("Film is already taken with code film : {}", codeFilm);
            throw new IllegalArgumentException(
                    String.format("Film is already taken with code film : %s", codeFilm));
        }
    }

    @Override
    public void addManyFilms(List<Films> film) {
        getLogger().info("Films has been successfully saved to the database");
        filmRepository.saveAll(film);
    }

    @Override
    public Films updateFilm(Films film) {
        Films updateFilm = filmRepository.findById(film.getFilmID())
                .orElseThrow(() -> new NotFoundExceptions("Film not found"));
        updateFilm.setTitle(film.getTitle());
        updateFilm.setDescription(film.getDescription());
        updateFilm.setOnShow(film.isOnShow());
        getLogger().info("Film {} has been successfully to update", film.getTitle());
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
        return filmRepository.findFilmsByCodeFilm(codeName)
                .orElseThrow( () -> new RuntimeException(
                        String.format("Film not found with code film : %s", codeName)));
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
    @Cacheable(value = "filmCache")
    public List<Films> findFilmByOnShow() {
        return filmRepository.findFilmsByOnShow();
    }

}

package com.rizky.cinematic.backend.model.mapper;

import com.rizky.cinematic.backend.model.dto.FilmDto;
import com.rizky.cinematic.backend.model.entity.Films;
import org.springframework.stereotype.Component;

@Component
public class FilmDtoToModel {

    public Films createToModel(FilmDto dto) {

        Films entity = new Films();

        entity.setCodeFilm(dto.getCodeFilm());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setOnShow(dto.isOnShow());
        return entity;
    }
}

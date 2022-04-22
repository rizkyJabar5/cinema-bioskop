package com.rizky.challenge4.backend.model.mapper;

import com.rizky.challenge4.backend.model.dto.FilmDto;
import com.rizky.challenge4.backend.model.entity.Films;
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

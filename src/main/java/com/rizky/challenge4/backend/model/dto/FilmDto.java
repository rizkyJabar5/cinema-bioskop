package com.rizky.challenge4.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FilmDto {

    private Long filmId;

    private String codeFilm;

    private String title;

    private String description;

    private boolean onShow;

    public FilmDto(String codeFilm, String title, String description, boolean onShow) {
        this.codeFilm = codeFilm;
        this.title = title;
        this.description = description;
        this.onShow = onShow;
    }
}

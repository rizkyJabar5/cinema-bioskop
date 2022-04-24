package com.rizky.challenge4.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FilmDto {


    @JsonProperty(value = "filmId", defaultValue = "1")
    private Long filmId;

    @JsonProperty(value = "codeFilm")
    private String codeFilm;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "onShow")
    private boolean onShow;

    public FilmDto(String codeFilm, String title, String description, boolean onShow) {
        this.codeFilm = codeFilm;
        this.title = title;
        this.description = description;
        this.onShow = onShow;
    }

    @Override
    public String toString() {
        return "FilmDto{" +
                "filmId=" + filmId +
                ", codeFilm='" + codeFilm + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", onShow=" + onShow +
                '}';
    }
}

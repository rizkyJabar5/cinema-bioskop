package com.rizky.challenge4.backend.controller;

import com.rizky.challenge4.backend.data.entity.Films;
import com.rizky.challenge4.backend.repository.FilmRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class FilmControllerTest {

    @Autowired
    private FilmController filmController;

    @MockBean
    private FilmRepository filmRepository;

    @Test
    @DisplayName("Test Tambah Film")
    public void addfilm() {
        String actual = filmController.addFilm(new Films(100L,"Soju", "Minuman berkhasiat", false));
        Films film = new Films();
        film.setFilmID(100L);
        film.setTitle("Soju");
        film.setDescription("Minuman berkhasiat");
        film.setOnShow(false);
        String expected = "List film [\nid=" + film.getFilmID() + ", \ntitle=" + film.getTitle() + ",\ndescription="
                + film.getDescription() + "\n]";
        System.out.println(expected + "\n" + actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test GET title Film")
    public void getUserType() {
        filmController.getAllFilms("Pocong Absurd");
    }


}
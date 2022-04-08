package com.rizky.challenge4.backend.controller;

import com.rizky.challenge4.backend.data.entity.Films;
import com.rizky.challenge4.backend.repository.FilmRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class FilmControllerTest {

    @Autowired
    FilmController filmController;

    @MockBean
    private FilmRepository filmRepository;

    @Test
    @DisplayName("Test Tambah Film")
    public void addfilm(){
        Films films = new Films();
        films.setTitle("Halilitanr");
        films.setDescription("aodhuiashbfduiasbfiopesdbp");
        films.setOnShow(false);
        filmController.addFilm(films);
    }

}
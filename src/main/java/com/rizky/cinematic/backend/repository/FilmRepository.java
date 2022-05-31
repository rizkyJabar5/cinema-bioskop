package com.rizky.cinematic.backend.repository;

import com.rizky.cinematic.backend.model.entity.Films;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Films, Long> {

    @Query("select f from Films f where f.codeFilm = ?1")
    Optional<Films> findFilmsByCodeFilm(String codeFilm);

    @Query("select f from Films f where f.title like concat('%', ?1, '%')")
    void findByTitleContaining(String title);

    @Query(value = "select f from Films f where f.onShow = true")
    List<Films> findFilmsByOnShow();

}

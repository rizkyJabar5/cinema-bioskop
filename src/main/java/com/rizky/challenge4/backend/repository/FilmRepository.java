package com.rizky.challenge4.backend.repository;

import com.rizky.challenge4.backend.data.entity.Films;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Films, Long> {

    Films findFilmsByTitle(String titleFilm);

    @Query("select f from Films f where f.title like concat('%', ?1, '%')")
    void findByTitleContaining(String title);

    @Query(value = "select f from Films f where f.onShow = true")
    List<Films> findFilmsByOnShow();

}

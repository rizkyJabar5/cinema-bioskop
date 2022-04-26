package com.rizky.challenge4.backend.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "films",
        uniqueConstraints = @UniqueConstraint(columnNames = "code_film"))
public class Films {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmID;

    @Column(name = "code_film", length = 100, nullable = false, unique = true)
    private String codeFilm;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "on_show")
    private boolean onShow;

    public Films(String codeFilm, String title, String description, boolean onShow) {
        this.codeFilm = codeFilm;
        this.title = title;
        this.description = description;
        this.onShow = onShow;
    }

    @Override
    public String toString() {
        return "Films{" +
                "filmID=" + filmID +
                ", codeFilm='" + codeFilm + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", onShow=" + onShow +
                '}';
    }

}

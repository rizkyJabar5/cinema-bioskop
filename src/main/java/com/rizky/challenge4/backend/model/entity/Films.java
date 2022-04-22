package com.rizky.challenge4.backend.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

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

    @OneToMany(mappedBy = "film")
    private Collection<Schedules> schedules;

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

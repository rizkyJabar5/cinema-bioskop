package com.rizky.challenge4.backend.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "films",
        uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class Films {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "film_id")
    private Long filmID;

    @Column(name = "title", length = 100, nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "on_show")
    private boolean onShow;

    public Films(String title, String description, boolean onShow) {
        this.title = title;
        this.description = description;
        this.onShow = onShow;
    }

    @Override
    public String toString() {
        return "List film [\nid=" + filmID + ", \ntitle=" + title + ",\ndescription=" + description + "\n]";
    }
}

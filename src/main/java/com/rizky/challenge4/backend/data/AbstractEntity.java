package com.rizky.challenge4.backend.data;

import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    public Long getId() {
        return id;
    }
}

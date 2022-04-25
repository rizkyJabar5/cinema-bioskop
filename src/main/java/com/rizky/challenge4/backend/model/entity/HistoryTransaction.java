package com.rizky.challenge4.backend.model.entity;

import com.rizky.challenge4.backend.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "history_transaction")
public class HistoryTransaction extends AbstractEntity {

    @ManyToOne
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "film_id")
    private Films film;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "seats_number", referencedColumnName = "seats_number"),
            @JoinColumn(name = "seats_row", referencedColumnName = "seats_row")
    })
    private Seats seats;

    @ManyToOne
    @JoinColumn(name = "schedules_id")
    private Schedules schedule;

}

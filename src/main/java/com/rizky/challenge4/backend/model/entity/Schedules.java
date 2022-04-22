package com.rizky.challenge4.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "schedules", uniqueConstraints = {
        @UniqueConstraint(columnNames = "film_id")
})
public class Schedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedulesId;

    @Column(name = "show_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Jakarta")
    private Date showDate;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm", timezone = "Asia/Jakarta")
    private Date startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm", timezone = "Asia/Jakarta")
    private Date endTime;

    @Column(name = "price")
    private Float price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "film_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Films film;

    @Override
    public String toString() {
        return "Schedule [\nid:" + getSchedulesId()+
                "\nShow Date:" + getShowDate() +
                "\nStart Time:" + getStartTime() +
                "\nEnd Time: " + getEndTime() +
                "\nPrice:" + getPrice() +
                "\nFilm: " + getFilm();
    }
}

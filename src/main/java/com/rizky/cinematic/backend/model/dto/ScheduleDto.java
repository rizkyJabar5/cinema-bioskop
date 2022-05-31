package com.rizky.cinematic.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rizky.cinematic.backend.model.entity.Films;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Setter
@Getter
@NoArgsConstructor
public class ScheduleDto {

    private Long schedulesId;
    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Jakarta")
    private Date showDate;
    @JsonFormat(pattern = "HH:mm", timezone = "Asia/Jakarta")
    private Date startTime;
    @JsonFormat(pattern = "HH:mm", timezone = "Asia/Jakarta")
    private Date endTime;
    private Float price;
    private Films film;

    private String titleFilm;

    private String onShow;

    public ScheduleDto(Date showDate, Date startTime, Date endTime, Float price, Films film) {
        this.showDate = showDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.film = film;
    }

    @Override
    public String toString() {
        return "ScheduleDto{" +
                "schedulesId=" + schedulesId +
                ", showDate='" + showDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", price=" + price +
                ", film=" + film +
                '}';
    }

}


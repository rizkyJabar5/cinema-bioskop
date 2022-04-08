package com.rizky.challenge4.backend.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ScheduleFilmDTO {
    private Long schedulesID;
    private Date showDate;
    private Date startTime;
    private Date endTime;
    private Float price;
    private String titleFilm;
    private String onShow = "Telah Tayang";
}

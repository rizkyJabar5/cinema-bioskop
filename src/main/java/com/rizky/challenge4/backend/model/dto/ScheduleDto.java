package com.rizky.challenge4.backend.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Setter
@Getter
@NoArgsConstructor
public class ScheduleDto {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMMM-yyyy");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm");

    private Long schedulesId;
    private Date showDate;
    private Date startTime;
    private Date endTime;
    private Float price;
    private String codeTitleFilm;
    private String onShow;

    public ScheduleDto(Date showDate, Date startTime, Date endTime, Float price) {
        this.showDate = showDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public Date getDateConverted(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(String.valueOf(this.showDate));
    }

    public Date getStartTimeConverted(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return timeFormat.parse(String.valueOf(this.startTime));
    }

    public Date getEndTimeConverted(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return timeFormat.parse(String.valueOf(this.startTime));
    }

    @Override
    public String toString() {
        return "ScheduleFilmDTO{" +
                "schedulesID=" + schedulesId +
                ", showDate=" + showDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + price +
                ", codeTitleFilm='" + codeTitleFilm + '\'' +
                ", onShow='" + onShow + '\'' +
                '}';
    }
}


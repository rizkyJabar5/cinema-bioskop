package com.rizky.challenge4.backend.data.mapper;

import com.rizky.challenge4.backend.data.dto.ScheduleFilmDTO;
import com.rizky.challenge4.backend.data.entity.Schedules;
import org.springframework.stereotype.Component;

@Component
public class ScheduleFilmConvert {

    public ScheduleFilmDTO convertScheduleFilmToDto(Schedules s){
        ScheduleFilmDTO schedules =  new ScheduleFilmDTO();
        schedules.setSchedulesID(s.getSchedulesID());
        schedules.setShowDate(s.getShowDate());
        schedules.setStartTime(s.getStartTime());
        schedules.setEndTime(s.getEndTime());
        schedules.setPrice(s.getPrice());
        schedules.setTitleFilm(s.getFilm().getTitle());

        return schedules;
    }
}

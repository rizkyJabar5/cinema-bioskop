package com.rizky.challenge4.backend.model.mapper;

import com.rizky.challenge4.backend.model.dto.ScheduleDto;
import com.rizky.challenge4.backend.model.entity.Schedules;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConvert {

    private final ScheduleDto schedules =  new ScheduleDto();

    public ScheduleDto getConvertToDto(Schedules s){

        schedules.setSchedulesId(s.getSchedulesId());
        schedules.setShowDate(s.getShowDate());
        schedules.setStartTime(s.getStartTime());
        schedules.setEndTime(s.getEndTime());
        schedules.setPrice(s.getPrice());
        schedules.setCodeTitleFilm(s.getFilm().getCodeFilm());
        if(!s.getFilm().isOnShow()){
            schedules.setOnShow("Belum Tayang");
        } else{
            schedules.setOnShow("Sudah Tayang");
        }

        return schedules;
    }

    public ScheduleDto setConvertToDto(Schedules entity){

        schedules.setShowDate(entity.getShowDate());
        schedules.setPrice(entity.getPrice());
        schedules.setStartTime(entity.getStartTime());
        schedules.setEndTime(entity.getEndTime());

        return schedules;
    }

}

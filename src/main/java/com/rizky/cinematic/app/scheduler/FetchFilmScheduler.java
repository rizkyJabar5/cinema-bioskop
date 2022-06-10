package com.rizky.cinematic.app.scheduler;

import com.rizky.cinematic.backend.service.FilmService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class FetchFilmScheduler extends QuartzJobBean {

    private final FilmService filmService;

    public FetchFilmScheduler(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        filmService.findFilmByOnShow();
    }
}

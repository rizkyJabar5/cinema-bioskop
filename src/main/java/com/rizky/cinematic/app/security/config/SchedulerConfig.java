package com.rizky.cinematic.app.security.config;

import com.rizky.cinematic.app.scheduler.FetchFilmScheduler;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import java.util.Objects;

@Configuration
public class SchedulerConfig {

    private final ApplicationContext applicationContext;

    @Autowired
    public SchedulerConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {

        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);

        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {

        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(springBeanJobFactory());

        JobDetail[] jobs = {filmFetchingDetail().getObject()};
        Trigger[] triggers = {filmFetchingTrigger().getObject()};

        schedulerFactoryBean.setJobDetails(jobs);
        schedulerFactoryBean.setTriggers(triggers);

        return schedulerFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean filmFetchingTrigger() {

        CronTriggerFactoryBean cronTrigger = new CronTriggerFactoryBean();
        cronTrigger.setJobDetail(Objects.requireNonNull(filmFetchingDetail().getObject()));
        cronTrigger.setName("Fetching film on show for every day at 12.00 AM");
        cronTrigger.setCronExpression("0 0 12 * * ?");

        return cronTrigger;
    }

    private JobDetailFactoryBean filmFetchingDetail() {

        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(FetchFilmScheduler.class);
        jobDetail.setName("Fetching film scheduler");
        jobDetail.setDescription("Fetching film for scheduler and insert into cache");
        jobDetail.setDurability(true);

        return jobDetail;
    }

}

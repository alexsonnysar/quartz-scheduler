package com.example.quartzscheduler.config;

import com.example.quartzscheduler.job.RecordRetentionJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(RecordRetentionJob.class)
                .withIdentity("recordRetentionJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("recordRetentionTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ?")) // Every minute
                .build();
    }
}

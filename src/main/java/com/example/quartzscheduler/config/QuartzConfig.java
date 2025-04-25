package com.example.quartzscheduler.config;

import com.example.quartzscheduler.job.RecordRetentionJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    public JobDetail createJobDetail(Class<? extends Job> jobClass, String jobName) {
        return JobBuilder.newJob(jobClass)
                .withIdentity(jobName)
                .storeDurably()
                .build();
    }

    public Trigger createTrigger(JobDetail jobDetail, String triggerName, String cronExpression) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(triggerName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
    }

    @Bean
    public JobDetail recordRetentionJobDetail() {
        return createJobDetail(RecordRetentionJob.class, "recordRetentionJob");
    }

    @Bean
    public Trigger recordRetentionTrigger(JobDetail recordRetentionJobDetail) {
        return createTrigger(recordRetentionJobDetail, "recordRetentionTrigger", "0 * * * * ?");
    }
}

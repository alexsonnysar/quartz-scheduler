package com.example.quartzscheduler.job;

import com.example.quartzscheduler.service.RecordRetentionPolicyService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class RecordRetentionJob implements Job {
    private final RecordRetentionPolicyService recordRetentionPolicyService;

    public RecordRetentionJob(RecordRetentionPolicyService recordRetentionPolicyService) {
        this.recordRetentionPolicyService = recordRetentionPolicyService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        recordRetentionPolicyService.executeRetentionPolicy();
        System.out.println("Record retention job executed.");
    }
}

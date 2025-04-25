package com.example.quartzscheduler.job;

import com.example.quartzscheduler.service.RecordRetentionPolicyService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecordRetentionJob implements Job {

    private final RecordRetentionPolicyService recordRetentionPolicyService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        recordRetentionPolicyService.executeRetentionPolicy();
        System.out.println("Record retention job executed.");
    }
}

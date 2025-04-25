package com.example.quartzscheduler.service;

import com.example.quartzscheduler.entity.RecordRetentionPolicy;
import com.example.quartzscheduler.repository.RecordRetentionPolicyRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecordRetentionPolicyService {

    private final RecordRetentionPolicyRepository recordRetentionPolicyRepository;
    private final JdbcTemplate jdbcTemplate;

    public void executeRetentionPolicy() {
        List<RecordRetentionPolicy> policies = recordRetentionPolicyRepository.findAll();

        policies.forEach(this::deleteOldRecords);
    }

    private void deleteOldRecords(RecordRetentionPolicy policy) {
        String tableName = policy.getTableName();
        Integer retentionDays = policy.getRetentionDays();

        String queryString = "DELETE FROM public." + tableName +
                " WHERE created_date <= NOW() - INTERVAL '" + retentionDays + " day'";

        int rowsDeleted = jdbcTemplate.update(queryString);

        log.info("Deleted {} records from {} older than {} days.", rowsDeleted, tableName, retentionDays);
    }
}

package com.example.quartzscheduler.service;

import com.example.quartzscheduler.entity.RecordRetentionPolicy;
import com.example.quartzscheduler.repository.RecordRetentionPolicyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordRetentionPolicyService {
    private final RecordRetentionPolicyRepository recordRetentionPolicyRepository;
    private final JdbcTemplate jdbcTemplate;

    public RecordRetentionPolicyService(RecordRetentionPolicyRepository recordRetentionPolicyRepository, JdbcTemplate jdbcTemplate, EntityManager entityManager) {
        this.recordRetentionPolicyRepository = recordRetentionPolicyRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void executeRetentionPolicy() {
        List<RecordRetentionPolicy> policies = recordRetentionPolicyRepository.findAll();

        policies.forEach(this::deleteOldRecords);
    }

//    private void deleteOldRecords(RecordRetentionPolicy policy) {
//        String tableName = policy.getTableName();
//        Integer retentionDays = policy.getRetentionDays();
//
//        // Dynamically construct the query with retentionDays embedded
//        String queryString = "DELETE FROM public." + tableName +
//                " WHERE created_date <= NOW() - INTERVAL '" + retentionDays + " day'";
//
//        Query query = entityManager.createNativeQuery(queryString);
//        int rowsDeleted = query.executeUpdate();
//
//        System.out.println("Deleted " + rowsDeleted + " records from " + tableName + " older than " + retentionDays + " days.");
//    }

    private void deleteOldRecords(RecordRetentionPolicy policy) {
        String tableName = policy.getTableName();
        Integer retentionDays = policy.getRetentionDays();

        // Dynamically construct the query with retentionDays embedded in the INTERVAL clause
        String queryString = "DELETE FROM public." + tableName +
                " WHERE created_date <= NOW() - INTERVAL '" + retentionDays + " day'";

        int rowsDeleted = jdbcTemplate.update(queryString);

        System.out.println("Deleted " + rowsDeleted + " records from " + tableName + " older than " + retentionDays + " days.");
    }
}

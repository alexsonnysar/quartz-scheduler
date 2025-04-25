package com.example.quartzscheduler.service;

import com.example.quartzscheduler.entity.RecordRetentionPolicy;
import com.example.quartzscheduler.repository.RecordRetentionPolicyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordRetentionPolicyService {
    private final RecordRetentionPolicyRepository recordRetentionPolicyRepository;
    private final EntityManager entityManager;

    public RecordRetentionPolicyService(RecordRetentionPolicyRepository recordRetentionPolicyRepository, EntityManager entityManager) {
        this.recordRetentionPolicyRepository = recordRetentionPolicyRepository;
        this.entityManager = entityManager;
    }

    public void executeRetentionPolicy() {
        List<RecordRetentionPolicy> policies = recordRetentionPolicyRepository.findAll();

        policies.forEach(policy -> {
            deleteOldRecords(policy);
        });
    }

    private void deleteOldRecords(RecordRetentionPolicy policy) {
        String tableName = policy.getTableName();
        Integer retentionDays = policy.getRetentionDays();

        // Dynamically construct the query with retentionDays embedded
        String queryString = "DELETE FROM public." + tableName +
                " WHERE created_date <= NOW() - INTERVAL '" + retentionDays + " day'";

        Query query = entityManager.createNativeQuery(queryString);
        int rowsDeleted = query.executeUpdate();

        System.out.println("Deleted " + rowsDeleted + " records from " + tableName + " older than " + retentionDays + " days.");
    }
}

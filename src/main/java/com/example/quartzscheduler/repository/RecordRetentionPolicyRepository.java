package com.example.quartzscheduler.repository;

import com.example.quartzscheduler.entity.RecordRetentionPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRetentionPolicyRepository extends JpaRepository<RecordRetentionPolicy, Long> {
}

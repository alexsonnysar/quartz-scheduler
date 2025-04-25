package com.example.quartzscheduler.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "record_retention_policy", schema = "public")
public class RecordRetentionPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "retention_days")
    private Integer retentionDays;
}

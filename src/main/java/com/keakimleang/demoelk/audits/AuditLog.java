package com.keakimleang.demoelk.audits;

import jakarta.persistence.*;
import java.time.*;
import lombok.*;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@ToString
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private String resource;
    private String ipAddress;
    private String details;

    private LocalDateTime timestamp;

    // Getters and setters
}

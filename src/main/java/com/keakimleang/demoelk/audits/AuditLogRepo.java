package com.keakimleang.demoelk.audits;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface AuditLogRepo extends JpaRepository<AuditLog, Long> {
}


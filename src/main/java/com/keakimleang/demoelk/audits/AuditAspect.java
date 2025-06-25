package com.keakimleang.demoelk.audits;

import jakarta.servlet.http.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;
import lombok.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.context.request.*;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditLogRepo auditLogRepository;

    @AfterReturning(pointcut = "@annotation(auditable)", returning = "result")
    public void logAudit(JoinPoint joinPoint, Auditable auditable, Object result) {
        String ip = getClientIp();

        AuditLog log = new AuditLog();
        log.setAction(auditable.action());
        log.setResource(joinPoint.getSignature().toShortString());
        log.setIpAddress(ip);
        log.setTimestamp(LocalDateTime.now());
        log.setDetails(getArgsAsString(joinPoint));

        auditLogRepository.save(log);
    }

    private String getArgsAsString(JoinPoint joinPoint) {
        return Arrays.stream(joinPoint.getArgs())
                .map(arg -> arg != null ? arg.toString() : "null")
                .collect(Collectors.joining(", "));
    }

    private String getClientIp() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) return "unknown";
        HttpServletRequest request = attributes.getRequest();
        return request.getRemoteAddr();
    }
}

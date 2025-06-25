package com.keakimleang.demoelk.filters;

import jakarta.servlet.*;
import java.io.*;
import java.util.*;
import lombok.extern.slf4j.*;
import org.slf4j.*;
import org.springframework.stereotype.*;

@Slf4j
@Component
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, IOException {
        String traceId = UUID.randomUUID().toString();
        MDC.put("traceId", traceId);
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
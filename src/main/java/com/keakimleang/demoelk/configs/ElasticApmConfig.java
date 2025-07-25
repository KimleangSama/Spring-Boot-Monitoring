package com.keakimleang.demoelk.configs;

import co.elastic.apm.attach.*;
import jakarta.annotation.*;
import java.util.*;
import lombok.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

@Setter
@Configuration
@ConfigurationProperties(prefix = "elastic.apm")
@ConditionalOnProperty(value = "elastic.apm.enabled", havingValue = "true")
public class ElasticApmConfig {

    private static final String SERVER_URL_KEY = "server_url";
    private String serverUrl;

    private static final String SERVICE_NAME_KEY = "service_name";
    private String serviceName;

    private static final String SECRET_TOKEN_KEY = "secret_token";
    private String secretToken;

    private static final String ENVIRONMENT_KEY = "environment";
    private String environment;

    private static final String APPLICATION_PACKAGES_KEY = "application_packages";
    private String applicationPackages;

    private static final String LOG_LEVEL_KEY = "log_level";
    private String logLevel;

    @PostConstruct
    public void init() {
        Map<String, String> apmProps = new HashMap<>(6);
        apmProps.put(SERVER_URL_KEY, serverUrl);
        apmProps.put(SERVICE_NAME_KEY, serviceName);
        apmProps.put(SECRET_TOKEN_KEY, secretToken);
        apmProps.put(ENVIRONMENT_KEY, environment);
        apmProps.put(APPLICATION_PACKAGES_KEY, applicationPackages);
        apmProps.put(LOG_LEVEL_KEY, logLevel);
        apmProps.put("universal_profiling_integration_enabled", "true");
        ElasticApmAttacher.attach(apmProps);
    }
}

package com.keakimleang.demoelk;

import io.pyroscope.http.Format;
import io.pyroscope.javaagent.EventType;
import io.pyroscope.javaagent.PyroscopeAgent;
import io.pyroscope.javaagent.config.Config;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoElkApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoElkApplication.class, args);
    }

    @PostConstruct
    public void init() {
        PyroscopeAgent.start(
                new Config.Builder()
                        .setApplicationName("demo-elk")
                        .setProfilingEvent(EventType.ITIMER)
                        .setFormat(Format.JFR)
                        .setServerAddress("http://localhost:4040")
                        // Optionally, if authentication is enabled, specify the API key.
                        // .setBasicAuthUser("<User>")
                        // .setBasicAuthPassword("<Password>")
                        // Optionally, if you'd like to set allocation threshold to register events, in bytes. '0' registers all events
                        // .setProfilingAlloc("0")
                        .build()
        );
    }
}

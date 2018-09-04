package com.experiment.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.web.servlet.WebMvcMetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication(exclude = WebMvcMetricsAutoConfiguration.class)
@EnableZipkinServer
public class CustomZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomZipkinApplication.class, args);
    }
}

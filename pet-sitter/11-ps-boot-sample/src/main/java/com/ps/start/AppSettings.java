package com.ps.start;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

@Slf4j
@Data
@ConfigurationProperties(prefix = "app")
public class AppSettings {

    @NotNull
    private Integer port;

    @NotNull
    private Integer sessionTimeout;

    @NotNull
    private String context;

    @PostConstruct
    public void check() {
        log.info("Initialized [{}] [{}] [{}]", port, context, sessionTimeout);
    }
}
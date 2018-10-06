package com.ps.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

@Component
public class YamlConfBean implements EmbeddedServletContainerCustomizer {

    private final AppSettings appSettings;

    @Autowired
    public YamlConfBean(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(appSettings.getPort());
        container.setContextPath(appSettings.getContext());
    }
}


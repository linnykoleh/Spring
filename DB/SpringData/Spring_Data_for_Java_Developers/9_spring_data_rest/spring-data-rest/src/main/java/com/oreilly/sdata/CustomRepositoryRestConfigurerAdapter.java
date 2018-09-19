package com.oreilly.sdata;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.MediaType;

public class CustomRepositoryRestConfigurerAdapter extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath("myapi");
        config.setDefaultMediaType(MediaType.APPLICATION_JSON);
        config.setDefaultPageSize(5);
        super.configureRepositoryRestConfiguration(config);
    }
}
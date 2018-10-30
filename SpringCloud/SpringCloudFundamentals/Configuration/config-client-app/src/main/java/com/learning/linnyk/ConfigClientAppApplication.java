package com.learning.linnyk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@RefreshScope //Beans annotated @RefreshScope can be refreshed at runtime and any components that are using
              // them will get a new instance on the next method call, fully initialized and injected
              // with all dependencies.
public class ConfigClientAppApplication {

    private final ConfigClientAppConfiguration properties;

    @Value("${some.other.property}")
    private String someOtherProperty;

    @Autowired
    public ConfigClientAppApplication(ConfigClientAppConfiguration configClientAppConfiguration) {
        this.properties = configClientAppConfiguration;
    }

    public static void main(String[] args) {
		SpringApplication.run(ConfigClientAppApplication.class, args);
	}

	@RequestMapping
	public String printConfig(){
        final StringBuilder builder = new StringBuilder();
        builder.append(properties.getProperty());
        builder.append(" || ");
        builder.append(someOtherProperty);

        return builder.toString();
    }
}

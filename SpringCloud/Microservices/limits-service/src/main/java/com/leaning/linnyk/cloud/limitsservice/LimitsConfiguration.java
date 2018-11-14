package com.leaning.linnyk.cloud.limitsservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "limits-service")
public class LimitsConfiguration {

	private int maximum;
	private int minimum;
}

package com.leaning.linnyk.cloud.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leaning.linnyk.cloud.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {

	private final LimitsConfiguration limitsConfiguration;

	@Autowired
	public LimitsConfigurationController(LimitsConfiguration limitsConfiguration) {
		this.limitsConfiguration = limitsConfiguration;
	}

	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfiguration(){
		return new LimitConfiguration(limitsConfiguration.getMaximum(), limitsConfiguration.getMinimum());
	}
}

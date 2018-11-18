package com.leaning.linnyk.cloud.limitsservice;

import com.leaning.linnyk.cloud.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod = "fallbackMethodGetLimitsConfiguration")
    public LimitConfiguration getLimitsConfiguration() {
        throw new RuntimeException("The service not available");
    }

    private LimitConfiguration fallbackMethodGetLimitsConfiguration() {
        return new LimitConfiguration(12345, 12);
    }

}

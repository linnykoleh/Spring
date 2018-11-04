package com.learning.linnyk;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class RibbonServiceConfiguration {

    @Autowired
    private IClientConfig iClientConfig;

    @Bean
    public IPing iPing(IClientConfig iClientConfig) {
        return new PingUrl();
    }

    @Bean
    public IRule iRule(IClientConfig iClientConfig) {
        return new AvailabilityFilteringRule();
    }
}

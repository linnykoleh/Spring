package com.learning.linnyk;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@RestController
@EnableHystrix
@EnableHystrixDashboard
public class HystrixApplication {

    /**
     * 1. Hystrix Dashboard
     * <p>
     * http://localhost:8080/hystrix
     * <p>
     * 2. Then put stream url
     * <p>
     * http://localhost:8080/hystrix.stream
     */

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/startClient")
    @HystrixCommand(fallbackMethod = "failover", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
    })
    public List<String> startClient(@RequestParam long time) throws InterruptedException {
        Thread.sleep(time);
        final List<String> forObject = restTemplate.getForObject("http://localhost:8888/service", List.class);
        forObject.add("From http://localhost:8888/service");
        return forObject;
    }

    public List<String> failover(long time) {
        final List<String> forObject = restTemplate.getForObject("http://localhost:8889/service", List.class);
        forObject.add("From http://localhost:8889/service");
        return forObject;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
    }
}

package com.learning.linnyk;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SpringMicroservicesEurekaClient2Application {

    @Autowired
    private EurekaClient eurekaClient;

    @RequestMapping("/serviceInfo")
    public String serviceInfo() {
        final InstanceInfo myClient = eurekaClient.getNextServerFromEureka("MyClient", false);
        return myClient.getAppName();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringMicroservicesEurekaClient2Application.class, args);
    }

}

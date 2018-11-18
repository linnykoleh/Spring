package com.learning.linnyk.cloud.zipkindistributedtracingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
@EnableDiscoveryClient
public class ZipkinDistributedTracingServerApplication {

    /**
     * Zipkin UI url - localhost:9411
     */
    public static void main(String[] args) {
        SpringApplication.run(ZipkinDistributedTracingServerApplication.class, args);
    }
}

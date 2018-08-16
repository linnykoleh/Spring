package com.ps.config;

import com.ps.repo.stub.StubPetRepo;
import com.ps.repos.PetRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestAppConfig2 {

    @Bean(initMethod = "init")
    public PetRepo petRepo(){
        return new StubPetRepo();
    }
}

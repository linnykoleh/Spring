package com.ps.config;

import com.ps.services.PetService;
import com.ps.services.impl.SimplePetService;
import org.springframework.context.annotation.Bean;

public class PetConfigClass2 {

    @Bean
    public PetService simplePetService(){
        return new SimplePetService();
    }
}

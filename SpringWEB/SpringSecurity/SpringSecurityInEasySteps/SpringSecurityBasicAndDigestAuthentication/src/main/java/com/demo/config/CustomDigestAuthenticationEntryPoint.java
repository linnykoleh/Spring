package com.demo.config;

import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomDigestAuthenticationEntryPoint extends DigestAuthenticationEntryPoint{

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("Spring");
        setKey("Secret");
        setNonceValiditySeconds(10);
        super.afterPropertiesSet();
    }
    
}

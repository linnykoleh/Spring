package com.learning.linnyk.proxy.config;

import com.learning.linnyk.proxy.beans.Bean1;
import com.learning.linnyk.proxy.beans.Bean2;
import com.learning.linnyk.proxy.beans.Bean3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppProxyConfiguration {

    @Bean
    public Bean2 bean2() {
        return new Bean2();
    }

    @Bean
    public Bean1 bean1() {
        final Bean2 bean2 = bean2();
        return new Bean1(bean2);
    }

    @Bean
    public Bean3 bean3() {
        final Bean2 bean2 = bean2();
        return new Bean3(bean2);
    }

}

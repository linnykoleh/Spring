package com.learning.linnyk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
@RestController
@Import(DemoConfiguration.class)
@ImportResource("classpath*:simple-context.xml")
public class SpringMicroservicesApplication {

    @Autowired
    @Resource(name = "circus")
    public List<String> list;

    @RequestMapping("/")
    public String message() {
        return String.join(", ", list);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringMicroservicesApplication.class, args);
    }

}

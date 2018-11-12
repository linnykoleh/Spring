package com.learning.linnyk.restfulwebservices.filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping(value = "/filtering", produces = "application/json")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("value1", "value2", "value3");
    }
}

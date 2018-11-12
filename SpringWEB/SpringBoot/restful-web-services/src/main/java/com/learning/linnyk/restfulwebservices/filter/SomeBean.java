package com.learning.linnyk.restfulwebservices.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SomeBean {

    private String field1;
    private String field2;

    @JsonIgnore
    private String field3;
}

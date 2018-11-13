package com.learning.linnyk.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.linnyk.restfulwebservices.versioning.dto.Name;
import com.learning.linnyk.restfulwebservices.versioning.dto.PersonV1;
import com.learning.linnyk.restfulwebservices.versioning.dto.PersonV2;

@RestController
public class PersonVersioningController {

    @GetMapping("v1/person")
    public PersonV1 personV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(value = "person/param", params = "version=1")
    public PersonV1 personV1Param() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "person/param", params = "version=2")
    public PersonV2 personV2Param() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(value = "person/header", headers = "X-API-VERSION=1")
    public PersonV1 personV1Header() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "person/header", headers = "X-API-VERSION=2")
    public PersonV2 personV2Header() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(value = "person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 personV1Produces() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 personV2Produces() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}

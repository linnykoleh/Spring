package com.ps.ents;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

    @Id
    private BigInteger id;
    private String email;
    private String username;
    private String password;
    private Double rating;
    private Boolean active;
    private String firstName;
    private String lastName;

}

package com.learning.linnyk.restfulwebservices.user;

import lombok.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

    private int id;

    @Size(min = 2, message = "Name should has a least 2 characters")
    private String name;

    @Past
    private Date birthDay;

}

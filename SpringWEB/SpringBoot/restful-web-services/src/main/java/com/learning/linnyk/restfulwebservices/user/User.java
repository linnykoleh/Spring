package com.learning.linnyk.restfulwebservices.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@ApiModel(description = "All details about user") //Swagger Documentation about API
public class User {

    private int id;

    @Size(min = 2, message = "Name should has a least 2 characters")
    @ApiModelProperty(notes = "Name should has a least 2 characters") //Swagger Documentation about API
    private String name;

    @Past
    @ApiModelProperty(notes = "Birth date should be in the past") //Swagger Documentation about API
    private Date birthDay;

}

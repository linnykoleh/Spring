package com.learning.linnyk.restfulwebservices.user.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@ApiModel(description = "All details about user") //Swagger Documentation about API
@Entity
public class User {

	@Id
	@GeneratedValue
	private int id;

	@Size(min = 2, message = "Name should has a least 2 characters")
	@ApiModelProperty(notes = "Name should has a least 2 characters") //Swagger Documentation about API
	private String name;

	@Past
	@ApiModelProperty(notes = "Birth date should be in the past") //Swagger Documentation about API
	private Date birthDay;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;

}

package com.demo.to;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;

@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {

	@NotEmpty
	@Size(min=3,max=15)
	private String userName;

	@NotEmpty
	@Size(min=3,max=15)
	private String password;

	private String address;

	@Pattern(regexp="(^$|[0-9]{10})")
	@NotEmpty
	private String phone;

}

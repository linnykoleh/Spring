package com.spring4.linnyk.mvc.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.spring4.linnyk.mvc.validators.PhoneConstraintValidator;

@Documented
@Constraint(validatedBy = PhoneConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {

	String message() default "{Phone}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

package com.spring4.linnyk.mvc.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.spring4.linnyk.mvc.annotations.Phone;

public class PhoneConstraintValidator implements ConstraintValidator<Phone, String> {

	@Override
	public void initialize(Phone constraintAnnotation) {

	}

	@Override
	public boolean isValid(String phoneField, ConstraintValidatorContext context) {
		if(phoneField == null){
			return false;
		}
		return phoneField.matches("[0-9()-]*");
	}
}

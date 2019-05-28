package com.itf.schulung.springboot.fullstack.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

public class StreetValidator implements ConstraintValidator<StreetContraint, String> {
	@Override
    public void initialize(StreetContraint street) {
    }
	@Override
	public boolean isValid(String street, ConstraintValidatorContext context) {
		 return street.matches("^[a-zA-Z]/s[0-9]*$");
		          
	}

}

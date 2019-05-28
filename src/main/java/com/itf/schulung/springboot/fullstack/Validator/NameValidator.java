package com.itf.schulung.springboot.fullstack.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<NamesConstraint, String> {
	@Override
    public void initialize(NamesConstraint name) {
    }
	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		 return name.matches("^[a-zA-Z\\\\s]*$");
	}

}

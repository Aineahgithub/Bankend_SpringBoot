package com.itf.schulung.springboot.fullstack.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneContraint, String>{
	
	@Override
    public void initialize(PhoneContraint phone) {
    }
	@Override
	public boolean isValid(String phonenumber, ConstraintValidatorContext context) {
		return phonenumber != null && phonenumber.matches("[0-9]+")
		          && (phonenumber.length() > 8) && (phonenumber.length() < 14);
	}

}

package com.itf.schulung.springboot.fullstack.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {

	@Override
    public void initialize(EmailConstraint email) {
    }
	//email.matches("^[a-zA-Z\\\\s]*$")&&
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return email.contains("@") && (email.contains(".com")||email.contains(".de")|| email.contains(".net")
				||email.contains(".uk") ||email.contains(".gmx"));
	}

}

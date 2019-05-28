package com.itf.schulung.springboot.fullstack.Validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = StreetValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StreetContraint {
	String message() default "Invalid street name and number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

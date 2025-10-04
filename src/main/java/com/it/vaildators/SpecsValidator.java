package com.it.vaildators;

import java.util.Arrays;
import java.util.List;

import com.it.utilis.SystemConstants;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SpecsValidator implements ConstraintValidator<ValidSpecs, String>
{
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) 
	{
	    List<String> list = Arrays.asList(SystemConstants.SECIALIZATION);	
		return list.contains(value);
	}
}

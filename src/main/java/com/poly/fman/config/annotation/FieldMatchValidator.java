package com.poly.fman.config.annotation;

import java.util.Objects;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        final BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        final Object firstValue = beanWrapper.getPropertyValue(firstFieldName);
        final Object secondValue = beanWrapper.getPropertyValue(secondFieldName);
        
        return Objects.equals(firstValue, secondValue);
    }
}

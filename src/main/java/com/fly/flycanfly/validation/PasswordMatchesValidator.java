package com.fly.flycanfly.validation;

import com.fly.flycanfly.dto.UserAccountDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        UserAccountDto userAccountDto = (UserAccountDto) value;
        return userAccountDto.getPassword().equals(userAccountDto.getConfirmPassword());
    }
}

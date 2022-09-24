package com.fly.flycanfly.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})

public @interface PasswordMatches {
    String message() default "Password must match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package com.davy.trans.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.*;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = EmailCheckValidator.class)
public @interface EmailCheck {

    public String message() default "Email is invalid haha";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}

package com.davy.trans.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class EmailCheckValidator implements ConstraintValidator<EmailCheck, String> {
    List<String> checkedEmail = Arrays.asList("alen", "rbl", "srm");


    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (value == null || value.isBlank()) return false;

        for (String customer : checkedEmail) {
            if (value.contains(customer))
                return true;
        }

        return false;
    }
}

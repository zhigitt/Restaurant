package java12.validation.waiter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WaiterAgeValidator implements ConstraintValidator<WaiterAge, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        return false;
    }
}

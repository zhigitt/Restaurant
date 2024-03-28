package java12.validation.waiter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WaiterExpirenseValidator implements ConstraintValidator<WaiterExpirense, Integer> {

    @Override
    public boolean isValid(Integer experinse, ConstraintValidatorContext constraintValidatorContext) {
        return experinse >= 1;
    }
}

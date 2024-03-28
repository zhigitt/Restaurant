package java12.validation.chef;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ChefExperinseValidator implements ConstraintValidator<ChefExpirense, Integer> {

    @Override
    public boolean isValid(Integer experinse, ConstraintValidatorContext constraintValidatorContext) {
        return experinse >= 2;
    }
}

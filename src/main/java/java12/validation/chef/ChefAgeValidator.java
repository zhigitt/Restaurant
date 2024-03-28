package java12.validation.chef;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java12.validation.chef.ChefAge;

public class ChefAgeValidator implements ConstraintValidator<ChefAge, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}

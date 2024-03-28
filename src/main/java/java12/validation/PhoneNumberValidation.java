package java12.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = PhoneNumberValidator.class
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)

public @interface PhoneNumberValidation {
    String message() default "{Phone number start with '+996' and length 13 symbol}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

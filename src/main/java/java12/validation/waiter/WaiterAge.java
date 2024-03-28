package java12.validation.waiter;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java12.validation.PhoneNumberValidator;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = WaiterAgeValidator.class
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)

public @interface WaiterAge {
    String message() default "{Password 4 ton kop bolsun}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

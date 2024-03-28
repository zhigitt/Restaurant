package java12.validation.chef;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java12.validation.PhoneNumberValidator;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = ChefExperinseValidator.class
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)

public @interface ChefExpirense {
    String message() default "{Stajy 2 jyldan kop boluusu kerek.}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package java12.validation.waiter;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java12.validation.PasswordValidator;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = WaiterExpirenseValidator.class
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)

public @interface WaiterExpirense {
    String message() default "{Stajy 1 jyldan kop boluusu kerek}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

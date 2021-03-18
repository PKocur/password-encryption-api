package pl.pk99.encryptionapi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FieldMatchesValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMatches {
    String message() default "Fields do not match";

    String firstField() default "";

    String secondField() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package pl.pk99.encryptionapi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;

public class FieldMatchesValidator implements ConstraintValidator<FieldMatches, Object> {

    String firstFieldName;
    String secondFieldName;

    @Override
    public void initialize(FieldMatches constraintAnnotation) {
        firstFieldName = constraintAnnotation.firstField();
        secondFieldName = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(Object form, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return Objects.equals(getFieldValue(form, firstFieldName), getFieldValue(form, secondFieldName));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }

    private Object getFieldValue(Object form, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = form.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(form);
    }
}

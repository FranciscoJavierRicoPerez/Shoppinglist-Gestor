package es.franricodev.shopping_list_gestor_service.shoppinglist.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsShoppinglistActiveValidator.class)
public @interface IsShoppinglistActive {
    String message() default "The Shoppinglist is not active";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package es.franricodev.shopping_list_gestor_service.shoppinglist.validations;

import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class IsShoppinglistActiveValidator implements ConstraintValidator<IsShoppinglistActive, Long> {

    @Autowired
    private ShoppinglistService shoppinglistService;

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return shoppinglistService.verifyShoppinglistActive(aLong);
    }
}

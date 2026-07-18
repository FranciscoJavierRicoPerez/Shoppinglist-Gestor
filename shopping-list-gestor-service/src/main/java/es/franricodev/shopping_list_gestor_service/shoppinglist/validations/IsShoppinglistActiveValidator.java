package es.franricodev.shopping_list_gestor_service.shoppinglist.validations;

import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class IsShoppinglistActiveValidator implements ConstraintValidator<IsShoppinglistActive, Long> {

    @Autowired
    private ShoppinglistService shoppinglistService;

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Prevalidation if shoppinglist {} is active or not", aLong);
        return shoppinglistService.verifyShoppinglistActive(aLong);
    }
}

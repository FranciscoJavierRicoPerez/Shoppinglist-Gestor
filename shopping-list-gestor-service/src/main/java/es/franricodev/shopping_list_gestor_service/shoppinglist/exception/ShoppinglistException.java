package es.franricodev.shopping_list_gestor_service.shoppinglist.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppinglistException extends RuntimeException {
    public ShoppinglistException(String message){
        super(message);
    }
}

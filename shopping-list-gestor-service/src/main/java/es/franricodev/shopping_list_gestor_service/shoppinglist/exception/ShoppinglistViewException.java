package es.franricodev.shopping_list_gestor_service.shoppinglist.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppinglistViewException extends Exception{
    public ShoppinglistViewException(String message) { super(message);}
}

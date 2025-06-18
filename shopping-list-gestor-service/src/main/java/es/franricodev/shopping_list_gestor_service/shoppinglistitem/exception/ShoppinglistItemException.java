package es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShoppinglistItemException extends Exception{
    private String message;
}

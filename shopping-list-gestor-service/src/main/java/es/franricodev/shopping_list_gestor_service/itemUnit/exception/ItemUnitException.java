package es.franricodev.shopping_list_gestor_service.itemUnit.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemUnitException extends Exception{
    private String message;
    public ItemUnitException(String message) {
        this.message = message;
    }
}

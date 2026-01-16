package es.franricodev.shopping_list_gestor_service.product.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductException extends RuntimeException{
    private String message;
}

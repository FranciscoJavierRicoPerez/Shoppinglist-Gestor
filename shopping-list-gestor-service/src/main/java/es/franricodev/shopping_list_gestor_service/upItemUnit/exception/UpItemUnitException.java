package es.franricodev.shopping_list_gestor_service.upItemUnit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpItemUnitException extends RuntimeException {
    private String message;
}

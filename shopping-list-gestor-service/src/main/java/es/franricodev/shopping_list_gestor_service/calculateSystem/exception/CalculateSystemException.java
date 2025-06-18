package es.franricodev.shopping_list_gestor_service.calculateSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalculateSystemException extends Exception {
    private String message;
}

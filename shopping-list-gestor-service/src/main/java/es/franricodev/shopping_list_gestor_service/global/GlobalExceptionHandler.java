package es.franricodev.shopping_list_gestor_service.global;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseError;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistExceptionV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ShoppinglistExceptionV2.class)
    public ResponseEntity<ResponseError> handleShoppinglistException(ShoppinglistExceptionV2 e) {
        log.info("Detected and ShoppinglistExceptionV2 [{}], proceed to his management", e.getHttpDescription());
        ResponseError error = new ResponseError(
                e.getHttpCode(),
                e.getHttpDescription(),
                e.getMessage()
        );
        return new ResponseEntity<>(error, e.getStatus());
    }

}

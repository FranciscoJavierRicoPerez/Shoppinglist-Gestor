package es.franricodev.shopping_list_gestor_service.global;

import es.franricodev.shopping_list_gestor_service.constants.GeneralConstants;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseError;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistExceptionV2;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ShoppinglistExceptionV2.class)
    public ResponseEntity<ResponseError> handleShoppinglistV2Exception(ShoppinglistExceptionV2 e) {
        log.info("Detected a ShoppinglistExceptionV2 [{}], proceed to his management", e.getHttpDescription());
        ResponseError error = new ResponseError(
                e.getHttpCode(),
                e.getHttpDescription(),
                e.getMessage()
        );
        return new ResponseEntity<>(error, e.getStatus());
    }

    @ExceptionHandler(ShoppinglistException.class)
    public ResponseEntity<ResponseError> handleShopinglistException(ShoppinglistException e) {
        log.info("Detected a ShoppinglistException [{}], proceed to his management", e.getMessage());
        ResponseError error = new ResponseError(
                GeneralConstants.HTTP_404,
                GeneralConstants.DESC_404,
                e.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShoppinglistItemException.class)
    public ResponseEntity<ResponseError> handleShoppinglistItemException(ShoppinglistItemException e) {
        log.info("Detected a ShoppinglistItemException [{}], proceed to his management", e.getMessage());
        ResponseError error = new ResponseError(
                GeneralConstants.HTTP_404,
                GeneralConstants.DESC_404,
                e.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}

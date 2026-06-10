package es.franricodev.shopping_list_gestor_service.shoppinglist.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ShoppinglistExceptionV2 extends RuntimeException {
    private String httpCode;
    private String httpDescription;
    private HttpStatus status;
    public ShoppinglistExceptionV2(String message, String code, String specificDescription, HttpStatus httpStatus){
        super(message);
        httpCode = code;
        httpDescription = specificDescription;
        status = httpStatus;
    }
}

package es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDetailsDTO;
import lombok.Data;

@Data
public class ResponseGetShoppinglistDetails {
    private String message;
    private ShoppinglistDetailsDTO shoppinglistDetailsDTO;
}

package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseCreateShoppinglistItem {
    private Long idShoppinglistItemCreated; // Id of the new item created
    private boolean created; // Indicates if the item has been created correctly
    private Double shoppinglistItemCalculatedPrice; // SLI calculated price
    private Double totalPrice; // New shoppinglist price value

}

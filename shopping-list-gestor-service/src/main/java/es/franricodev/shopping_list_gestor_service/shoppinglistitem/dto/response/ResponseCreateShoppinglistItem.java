package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.ShoppinglistItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCreateShoppinglistItem {
    // private ShoppinglistItemDTO itemCreated;
    private Long idItemCreated;
    private boolean created;
    private String responseMessage;
}

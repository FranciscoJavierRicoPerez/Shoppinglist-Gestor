package es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseCreateShoppinglist {
    private ShoppinglistMetadata shoppinglistCreated;
}

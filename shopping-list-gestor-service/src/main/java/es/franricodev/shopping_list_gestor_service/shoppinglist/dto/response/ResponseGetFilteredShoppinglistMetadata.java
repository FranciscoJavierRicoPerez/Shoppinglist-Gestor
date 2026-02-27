package es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response;

import java.util.List;

public record ResponseGetFilteredShoppinglistMetadata(List<ShoppinglistMetadata> shoppinglistTableMetadataList) {
}

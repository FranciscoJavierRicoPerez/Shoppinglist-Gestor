package es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class ResponseGetShoppinglistTableMetadata {
    List<ShoppinglistMetadata> shoppinglistTableMetadataList;
}
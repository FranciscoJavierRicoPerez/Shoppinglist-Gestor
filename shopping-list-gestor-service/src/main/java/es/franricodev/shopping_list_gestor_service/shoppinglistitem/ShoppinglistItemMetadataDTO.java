package es.franricodev.shopping_list_gestor_service.shoppinglistitem;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppinglistItemMetadataDTO {
    private Long id;
    private String name;
    private String assignationToListDate;
    private String calculateSystemCode;
    private Double calculatedPrice;
}

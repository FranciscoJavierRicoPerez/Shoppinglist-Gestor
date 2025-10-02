package es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ShoppinglistMetadata {
    private Long idShoppinglist;
    private String code;
    private String creationDate;
    private Double totalPrice;
    private Boolean isActive;
}

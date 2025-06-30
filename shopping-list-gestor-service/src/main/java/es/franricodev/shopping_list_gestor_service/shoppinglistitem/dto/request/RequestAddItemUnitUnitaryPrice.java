package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request;

import lombok.Getter;

@Getter
public class RequestAddItemUnitUnitaryPrice {
    private Long shoppinglistItemId;
    private Double price;
    private Integer quantity;
}

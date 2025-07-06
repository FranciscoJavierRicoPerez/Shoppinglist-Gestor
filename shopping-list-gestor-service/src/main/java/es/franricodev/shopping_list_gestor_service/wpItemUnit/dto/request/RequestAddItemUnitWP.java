package es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request;

import lombok.Data;

@Data
public class RequestAddItemUnitWP {
    private Long idShoppinglistItem;
    private Double priceKg;
    private Double weight;
}

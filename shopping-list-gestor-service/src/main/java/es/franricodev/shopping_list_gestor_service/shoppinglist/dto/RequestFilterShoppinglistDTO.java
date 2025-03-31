package es.franricodev.shopping_list_gestor_service.shoppinglist.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequestFilterShoppinglistDTO {
    private String createDate;
    private String closeDate;
    private String code;
    private Double totalPrice;
    private Boolean isActive;
}

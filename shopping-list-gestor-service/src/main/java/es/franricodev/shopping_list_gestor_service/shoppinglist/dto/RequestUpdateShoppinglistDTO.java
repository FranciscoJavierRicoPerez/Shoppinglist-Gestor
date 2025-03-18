package es.franricodev.shopping_list_gestor_service.shoppinglist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdateShoppinglistDTO {
    private Long id;
    private String code;
    private Double totalPrice;
    private Boolean isActive;
}

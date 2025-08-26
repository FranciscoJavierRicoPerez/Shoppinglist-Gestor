package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class ResponseItemUnitUpGrouped {
    private Integer quantity;
    private Double price;
    private Double calculatedPrice;
}

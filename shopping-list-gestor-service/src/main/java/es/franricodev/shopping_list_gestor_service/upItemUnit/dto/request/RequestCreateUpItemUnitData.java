package es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request;

import lombok.Data;

@Data
public class RequestCreateUpItemUnitData {
    private Integer quantity;
    private Double unitaryPrice;
}

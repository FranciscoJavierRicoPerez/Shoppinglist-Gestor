package es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request;

import lombok.Data;

@Data
public class RequestCreateUpItemUnitData {
    private Long idItemUnitUp; // -- ESTE VALOR CREO QUE NO SIRVE PARA NADA
    private Integer quantity;
    private Double unitaryPrice;
}

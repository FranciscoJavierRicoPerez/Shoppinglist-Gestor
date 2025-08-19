package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public class ResponseGetAllItemUnitUpGroupedByPrice {
    private Double totalPrice;
    private List<ResponseItemUnitUpGrouped> itemsUpGrouped;
}

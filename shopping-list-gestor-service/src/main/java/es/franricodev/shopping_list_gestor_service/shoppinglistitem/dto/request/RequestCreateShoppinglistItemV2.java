package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request;

import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.product.dto.request.CreateProductInfo;
import lombok.Data;

@Data
public class RequestCreateShoppinglistItemV2 {
    private CreateProductInfo productInfo;
    private Long selectedCalculateSystem;
    private CreateItemUnitData createItemUnitData;
}

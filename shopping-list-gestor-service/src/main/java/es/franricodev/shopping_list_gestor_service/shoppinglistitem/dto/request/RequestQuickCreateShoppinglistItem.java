package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request;

import es.franricodev.shopping_list_gestor_service.product.dto.request.CreateProductInfo;

public record RequestQuickCreateShoppinglistItem(
        CreateProductInfo createProductInfo,
        Long selectedCalculateSystem
) {
}

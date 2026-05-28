package es.franricodev.shopping_list_gestor_service.shoppinglist.service;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;

public interface ShoppinglistV3Service {

    ResponseDeleteShoppinglistItem deleteShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem);

}

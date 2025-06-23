package es.franricodev.shopping_list_gestor_service.shoppinglistitem.service;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.ShoppinglistItemDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;

public interface ShoppinglistItemService {

    ShoppinglistItemDTO createShoppinglistItem(RequestCreateShoppinglistItem requestCreateShoppinglistItem, Long idShoppinglist) throws ShoppinglistItemException;

    void deleteShoppinglistItem(Long idItem) throws ShoppinglistItemException;

}

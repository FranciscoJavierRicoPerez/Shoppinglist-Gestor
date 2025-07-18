package es.franricodev.shopping_list_gestor_service.shoppinglistitem.service;

import es.franricodev.shopping_list_gestor_service.itemUnit.dto.ItemUnitDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.ShoppinglistItemDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItemV2;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestAddItemUnitWP;

import java.util.List;

public interface ShoppinglistItemService {

    ShoppinglistItemDTO createShoppinglistItem(RequestCreateShoppinglistItem requestCreateShoppinglistItem, Long idShoppinglist) throws ShoppinglistItemException;

    void deleteShoppinglistItem(Long idItem) throws ShoppinglistItemException;

    void addItemUnitToShoppinglistItem(Long idItem, Double unitaryPrice, Integer quantity) throws ShoppinglistItemException;

    List<ItemUnitDTO> getAllItemUnitsFromShoppinglistItem(Long idShoppinglistItem) throws ShoppinglistItemException;

    void removeItemUnitFromShoppinglistItem(Long idShoppinglistItem, Long idItemUnit) throws ShoppinglistItemException;

    void addItemUnitWPToShoppinglistItem(Long idShoppinglistItem, RequestAddItemUnitWP requestAddItemUnitWP) throws ShoppinglistItemException;

    // **************************** VERSION 2 ENPOINTS ***************************************
    void createShoppinglistItemV2(Long idShoppinglist, RequestCreateShoppinglistItemV2 requestCreateShoppinglistItemV2) throws ShoppinglistItemException;
}

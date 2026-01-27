package es.franricodev.shopping_list_gestor_service.shoppinglistitem.service;

import es.franricodev.shopping_list_gestor_service.itemUnit.dto.ItemUnitDTO;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.itemUnit.exception.ItemUnitException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.ShoppinglistItemMetadataDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItemV2;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.*;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestAddItemUnitWP;

import java.util.List;

public interface ShoppinglistItemService {

    void addItemUnitToShoppinglistItem(CreateItemUnitData createItemUnitData, Long idShoppinglistItem) throws ShoppinglistItemException, ItemUnitException, ShoppinglistException;

    List<ItemUnitDTO> getAllItemUnitsFromShoppinglistItem(Long idShoppinglistItem) throws ShoppinglistItemException;

    void removeItemUnitFromShoppinglistItem(Long idShoppinglistItem, Long idItemUnit) throws ShoppinglistItemException;

    void addItemUnitWPToShoppinglistItem(Long idShoppinglistItem, RequestAddItemUnitWP requestAddItemUnitWP) throws ShoppinglistItemException;

    ResponseGetAllItemUnitUpGroupedByPrice getItemsUnitsUpGroupedByPrice(Long idShoppinglistItem) throws ShoppinglistItemException;

    ShoppinglistItem updateShoppinglistItem(ShoppinglistItem shoppinglistItem);

    ResponseItemUnitWpMetadata getItemUnitWpMetadata(Long idShoppinglistItem) throws ShoppinglistItemException;

    void deleteLogicAllShoppinglistItem(List<ShoppinglistItem> shoppinglistItemList);

    void deleteLogicShoppinglistItem(ShoppinglistItem shoppinglistItem);

    ResponseDeleteShoppinglistItem deleteLogicShoppinglistItemById(Long idShoppinglistItem) throws ShoppinglistItemException;

    ShoppinglistItem findShoppinglistItemById(Long idShoppinglistItem) throws ShoppinglistItemException;

    ResponseCreateShoppinglistItem createShoppinglistItemMetadata(RequestCreateShoppinglistItemV2 requestCreateShoppinglistItem) throws ShoppinglistItemException;

    List<ShoppinglistItemMetadataDTO> getShoppinglistItemMetadataDTO(Long idShoppinglist) throws ShoppinglistItemException;

    // ResponseGetItemUnitWpMetadata getItemUnitWpMetadata(Long idShoppinglist) throws ShoppinglistItemException;

}



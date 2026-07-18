package es.franricodev.shopping_list_gestor_service.shoppinglist.service;

import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetAllItemsUnit;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestUpdateShoppinglistItemItemUnitsUp;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseGetAllItemUnitUpGroupedByPrice;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseItemUnitWpMetadata;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestAddItemUnitWP;

public interface ShoppinglistV3Service {

    ResponseDeleteShoppinglistItem deleteShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem);

    void addItemUnitUpToShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem, CreateItemUnitData request);

    void deleteItemUnitFromShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem, Long idItemUnit);

    ResponseGetAllItemsUnit getAllItemUnitsFromShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem);

    void addItemUnitWpToShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem, RequestAddItemUnitWP request);

    ResponseGetAllItemUnitUpGroupedByPrice getItemsUnitGroupedByPrice(Long idShoppinglist, Long idShoppinglistItem);

    ResponseItemUnitWpMetadata getItemUnitsWpMetadata(Long idShoppinglist, Long idShoppinglistItem);

    void updateItemUnitUpDataFromShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem, RequestUpdateShoppinglistItemItemUnitsUp request);


}

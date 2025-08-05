package es.franricodev.shopping_list_gestor_service.itemUnit.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.itemUnit.exception.ItemUnitException;
import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestAddItemUnitWP;

public interface ItemUnitService {

    ItemUnit createItemUnit(ShoppinglistItem shoppinglistItem, Double unitaryPrice, CalculateSystem calculateSystem);

    ItemUnit findItemUnitById(Long id) throws Exception;

    void deleteItemUnit(ItemUnit itemUnit);

    void updateItemUnit(ItemUnit itemUnit, RequestAddItemUnitWP requestAddItemUnitWP);

    Double calculateItemUnitTotalPrice(ItemUnit itemUnit);

    // ************************* V2 **********************
    ItemUnit createItemUnitV2(CreateItemUnitData createItemUnitData, boolean isWpItemUnit, ShoppinglistItem shoppinglistItem) throws ItemUnitException;

}

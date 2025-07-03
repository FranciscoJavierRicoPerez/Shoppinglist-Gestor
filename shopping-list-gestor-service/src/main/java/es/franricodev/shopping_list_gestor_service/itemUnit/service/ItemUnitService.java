package es.franricodev.shopping_list_gestor_service.itemUnit.service;

import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;

public interface ItemUnitService {

    ItemUnit createItemUnit(ShoppinglistItem shoppinglistItem, Double unitaryPrice);

    ItemUnit findItemUnitById(Long id) throws Exception;

    void deleteItemUnit(ItemUnit itemUnit);

}

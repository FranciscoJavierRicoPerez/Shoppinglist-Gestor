package es.franricodev.shopping_list_gestor_service.shoppinglist.controller.v3.impl;

import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api.v3.ApiShoppinglistV3Constants;
import es.franricodev.shopping_list_gestor_service.shoppinglist.controller.v3.ShoppinglistV3Controller;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistV3Service;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestUpdateShoppinglistItemItemUnitsUp;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestAddItemUnitWP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(ApiShoppinglistV3Constants.BASE_URL)
public class ShoppinglistV3ControllerImpl implements ShoppinglistV3Controller {

    @Autowired
    private ShoppinglistV3Service shoppinglistV3Service;

    /**
     * Logic delete of a shoppinglist item
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @return
     */
    @Override
    public ResponseEntity<?> deleteShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem) {
        log.info("Delete the SHOPPINGLIST_ITEM with id: {}", idShoppinglistItem);
        return ResponseEntity.ok(shoppinglistV3Service.deleteShoppinglistItem(idShoppinglist,idShoppinglistItem));
    }

    /**
     * Add a new item unit up to the shoppinglist item
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<?> addItemUnitUpToShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem, CreateItemUnitData request) {
        log.info("Add ITEM_UNIT_UP to SHOPPINGLIST_ITEM with id: [{}] from SHOPPINGLIST with id: [{}]", idShoppinglistItem, idShoppinglist);
        Double newSliCalculatedPrice = shoppinglistV3Service.addItemUnitUpToShoppinglistItem(idShoppinglist, idShoppinglistItem, request);
        return new ResponseEntity<>(newSliCalculatedPrice, HttpStatus.CREATED);
    }

    /**
     * Delete item unit from shoppinglist item
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @param idItemUnit
     * @return
     */
    @Override
    public ResponseEntity<?> deleteItemUnitFromShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem, Long idItemUnit) {
        log.info("Remove ITEM_UNIT with id: [{}] from the SHOPPINGLIST_ITEM with id: [{}] in the SHOPPINGLIST with id: [{}]", idItemUnit, idShoppinglistItem, idShoppinglist);
        shoppinglistV3Service.deleteItemUnitFromShoppinglistItem(idShoppinglist, idShoppinglistItem, idItemUnit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Gives all the items units from the shoppinglist item
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @return
     */
    @Override
    public ResponseEntity<?> getAllItemUnitsFromShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem) {
        log.info("Get all ITEMS UNITS from the SHOPPINGLIST ITEM with id: [{}] from the SHOPPINGLIST with id: [{}]", idShoppinglistItem, idShoppinglist);
        return ResponseEntity.ok(shoppinglistV3Service.getAllItemUnitsFromShoppinglistItem(idShoppinglist, idShoppinglistItem));
    }

    /**
     * Add a new item unit wp to the shoppinglist item
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @param requestAddItemUnitWP
     * @return
     */
    @Override
    public ResponseEntity<?> addItemUnitWpToShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem, RequestAddItemUnitWP requestAddItemUnitWP) {
        log.info("Add ITEM UNIT WP to the SHOPPINGLIST_ITEM with id: [{}] from the SHOPPINGLIST with id: [{}]", idShoppinglistItem, idShoppinglistItem);
        shoppinglistV3Service.addItemUnitWpToShoppinglistItem(idShoppinglist, idShoppinglistItem, requestAddItemUnitWP);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Gives all items unit up grouped by price
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @return
     */
    @Override
    public ResponseEntity<?> getAllItemsUnitUpGroupedByPrice(Long idShoppinglist, Long idShoppinglistItem) {
        log.info("Get all items units up grouped by price from SHOPPINGLIST_ITEM with id: [{}] from the SHOPPINGLIST with id: [{}]", idShoppinglistItem, idShoppinglist);
        return ResponseEntity.ok(shoppinglistV3Service.getItemsUnitGroupedByPrice(idShoppinglist, idShoppinglistItem));
    }

    /**
     * Gives the metadata from an item unit wp
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @return
     */
    @Override
    public ResponseEntity<?> getItemUnitsWpMetadata(Long idShoppinglist, Long idShoppinglistItem) {
        log.info("Get items units wp metadata from SHOPPINGLIST_ITEM with id: [{}] from the SHOPPINGLIST with id: [{}]", idShoppinglistItem, idShoppinglist);
        return ResponseEntity.ok(shoppinglistV3Service.getItemUnitsWpMetadata(idShoppinglist, idShoppinglistItem));
    }

    /**
     * Update item unit up data from a shoppinglist item
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<?> updateItemUnitUpDataFromShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem, RequestUpdateShoppinglistItemItemUnitsUp request) {
        log.info("Update item unit up data from SHOPPINGLIST_ITEM with id: [{}] from the SHOPPINGLIST with id: [{}]", idShoppinglistItem, idShoppinglist);
        shoppinglistV3Service.updateItemUnitUpDataFromShoppinglistItem(idShoppinglist, idShoppinglistItem, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

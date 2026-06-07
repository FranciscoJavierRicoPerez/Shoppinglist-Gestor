package es.franricodev.shopping_list_gestor_service.shoppinglist.controller.v3.impl;

import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api.v3.ApiShoppinglistV3Constants;
import es.franricodev.shopping_list_gestor_service.shoppinglist.controller.v3.ShoppinglistV3Controller;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistV3Service;
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

    @Override
    public ResponseEntity<?> addItemUnitUpToShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem, CreateItemUnitData request) {
        log.info("Add ITEM_UNIT_UP to SHOPPINGLIST_ITEM with id: [{}] from SHOPPINGLIST with id: [{}]", idShoppinglistItem, idShoppinglist);
        shoppinglistV3Service.addItemUnitUpToShoppinglistItem(idShoppinglist, idShoppinglistItem, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

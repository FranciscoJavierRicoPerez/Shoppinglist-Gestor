package es.franricodev.shopping_list_gestor_service.shoppinglist.controller.v3.impl;

import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api.v3.ApiShoppinglistV3Constants;
import es.franricodev.shopping_list_gestor_service.shoppinglist.controller.v3.ShoppinglistV3Controller;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistV3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(ApiShoppinglistV3Constants.BASE_URL)
public class ShoppinglistV3ControllerImpl implements ShoppinglistV3Controller {

    @Autowired
    private ShoppinglistV3Service shoppinglistV3Service;

    @Override
    public ResponseEntity<?> deleteShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem) {
        log.info("Delete the shoppinglist item with id: {}", idShoppinglistItem);
        return ResponseEntity.ok(shoppinglistV3Service.deleteShoppinglistItem(idShoppinglist,idShoppinglistItem));
    }
}

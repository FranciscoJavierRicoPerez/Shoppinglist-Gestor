package es.franricodev.shopping_list_gestor_service.shoppinglistitem.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistService;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.service.ShoppinglistItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppinglistitem")
public class ShoppinglistItemController {

    @Autowired
    private ShoppinglistItemService shoppinglistItemService;

    private final static Logger logger = LoggerFactory.getLogger(ShoppinglistItemController.class);

    @PostMapping("/{id_shoppinglist}/createItem")
    public ResponseEntity<ResponseCreateShoppinglistItem> createShoppinglistItem(
            @PathVariable(name = "id_shoppinglist") Long idShoppinglist,
            @RequestBody RequestCreateShoppinglistItem requestCreateShoppinglistItem) {
        logger.info("Create a new shoppinglist item");

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}

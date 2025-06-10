package es.franricodev.shopping_list_gestor_service.shoppinglistitem.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.ShoppinglistItemDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.messages.ShoppinglistItemMessagesError;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.messages.ShoppinglistItemMessagesSuccess;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.service.ShoppinglistItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shoppinglistitem")
public class ShoppinglistItemController {

    @Autowired
    private ShoppinglistItemService shoppinglistItemService;

    private final static Logger logger = LoggerFactory.getLogger(ShoppinglistItemController.class);

    @PostMapping("/v1/{id_shoppinglist}/createItem")
    public ResponseEntity<ResponseCreateShoppinglistItem> createShoppinglistItem(
            @PathVariable(name = "id_shoppinglist") Long idShoppinglist,
            @RequestBody RequestCreateShoppinglistItem requestCreateShoppinglistItem) {
        logger.info("Create a new shoppinglist item");
        ResponseCreateShoppinglistItem response = new ResponseCreateShoppinglistItem();
        HttpStatus httpStatus = HttpStatus.CREATED;
        try {
             response.setItemCreated(shoppinglistItemService.createShoppinglistItem(requestCreateShoppinglistItem, idShoppinglist));
             response.setResponseMessage(ShoppinglistItemMessagesSuccess.SHOPPINGLISTITEM_CREATED_OK);
        } catch (ShoppinglistItemException e) {
           response.setItemCreated(null);
           response.setResponseMessage(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_CREATE_ERR);
           httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, httpStatus);
    }

}

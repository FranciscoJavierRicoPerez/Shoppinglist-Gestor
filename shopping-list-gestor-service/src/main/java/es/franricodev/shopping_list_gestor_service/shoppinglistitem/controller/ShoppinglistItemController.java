package es.franricodev.shopping_list_gestor_service.shoppinglistitem.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestAddItemUnitUnitaryPrice;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;
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

@CrossOrigin(origins = {"http://localhost:8100/", "http://192.168.18.7:9000/", "*"})
@RestController
@RequestMapping("/api/shoppinglistitem")
public class ShoppinglistItemController {

    @Autowired
    private ShoppinglistItemService shoppinglistItemService;

    private final static Logger logger = LoggerFactory.getLogger(ShoppinglistItemController.class);

    @PostMapping("/v1/{idShoppinglist}/createItem")
    public ResponseEntity<ResponseCreateShoppinglistItem> createShoppinglistItem(
            @PathVariable(name = "idShoppinglist") Long idShoppinglist,
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

    @DeleteMapping("/v1/{idItem}/delete")
    public ResponseEntity<ResponseDeleteShoppinglistItem> deleteShoppinglistItem(@PathVariable("idItem") Long idItem) {
        logger.info("Delete the shoppinglist item with id: {}", idItem);
        ResponseDeleteShoppinglistItem responseDeleteShoppinglistItem = new ResponseDeleteShoppinglistItem();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            shoppinglistItemService.deleteShoppinglistItem(idItem);
            responseDeleteShoppinglistItem.setDelete(true);
            responseDeleteShoppinglistItem.setMessage(ShoppinglistItemMessagesSuccess.SHOPPINGLISTITEM_DELETED_OK);
        } catch (ShoppinglistItemException e) {
            responseDeleteShoppinglistItem.setDelete(false);
            responseDeleteShoppinglistItem.setMessage(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_DELETED_ERR);
        }
        return new ResponseEntity<>(responseDeleteShoppinglistItem, httpStatus);
    }

    @PostMapping("/v1/{idItem}/addItemUnit")
    public ResponseEntity<?> addItemUnit(@PathVariable("idItem") Long idItem, @RequestBody RequestAddItemUnitUnitaryPrice request) throws ShoppinglistItemException {
        logger.info("Creating a new item unit for the shoppinglist item with id: {}", idItem);
        shoppinglistItemService.addItemUnitToShoppinglistItem(request.getShoppinglistItemId(), request.getPrice(), request.getQuantity());
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/v1/{idItem}/removeItemUnit/{idItemUnit}")
    public ResponseEntity<?> removeItemUnit(@PathVariable("idItem") Long idItem, @PathVariable("idItemUnit") Long idItemUnit) {
        return ResponseEntity.ok(null);
    }


}

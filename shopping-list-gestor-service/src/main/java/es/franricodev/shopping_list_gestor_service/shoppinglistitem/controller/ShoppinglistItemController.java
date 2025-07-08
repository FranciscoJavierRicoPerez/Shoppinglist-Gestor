package es.franricodev.shopping_list_gestor_service.shoppinglistitem.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetAllItemsUnit;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestAddItemUnitUnitaryPrice;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.messages.ShoppinglistItemMessagesError;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.messages.ShoppinglistItemMessagesSuccess;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.service.ShoppinglistItemService;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestAddItemUnitWP;
import lombok.Getter;
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
             response.setIdItemCreated(shoppinglistItemService.createShoppinglistItem(requestCreateShoppinglistItem, idShoppinglist).getId());
             response.setCreated(true);
             response.setResponseMessage(ShoppinglistItemMessagesSuccess.SHOPPINGLISTITEM_CREATED_OK);
        } catch (ShoppinglistItemException e) {
           response.setIdItemCreated(null);
           response.setResponseMessage(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_CREATE_ERR);
           response.setCreated(false);
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

    @PostMapping("/v1/{idShoppinglistItem}/addItemUnitUP")
    public ResponseEntity<Void> addItemUnit(@PathVariable("idShoppinglistItem") Long idShoppinglistItem, @RequestBody RequestAddItemUnitUnitaryPrice request) {
        logger.info("Creating a new item unit UP for the shoppinglist item with id: {}", idShoppinglistItem);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            shoppinglistItemService.addItemUnitToShoppinglistItem(request.getShoppinglistItemId(), request.getPrice(), request.getQuantity());
        } catch (ShoppinglistItemException e) {
            httpStatus =  HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(httpStatus);
    }

    @DeleteMapping("/v1/{idItem}/removeItemUnit/{idItemUnit}")
    public ResponseEntity<?> removeItemUnit(@PathVariable("idItem") Long idItem, @PathVariable("idItemUnit") Long idItemUnit) {
        logger.info("Removing the item unit with id: {} from the shoppinglist item with id: {}", idItem, idItemUnit);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            shoppinglistItemService.removeItemUnitFromShoppinglistItem(idItem, idItemUnit);
        } catch (ShoppinglistItemException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(httpStatus);
    }

    @GetMapping("/v1/{idShoppinglistItem}/itemsUnits")
    public ResponseEntity<ResponseGetAllItemsUnit> getAllItemUnitsFromShoppinglistItem(@PathVariable("idShoppinglistItem") Long idShoppinglistItem) {
        logger.info("Getting all the items units with calculate system UP of shoppinglist item with id: {}", idShoppinglistItem);
        ResponseGetAllItemsUnit responseGetAllItemsUnit = new ResponseGetAllItemsUnit();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            responseGetAllItemsUnit.setItemUnitList( shoppinglistItemService.getAllItemUnitsFromShoppinglistItem(idShoppinglistItem));
            responseGetAllItemsUnit.setMessage("ITEMS UNITS OBTENIDOS CON EXITO");
        } catch (ShoppinglistItemException e){
            httpStatus =  HttpStatus.BAD_REQUEST;
            responseGetAllItemsUnit.setMessage("ERROR OBTENIENDO LOS ITEMS UNIT");
        }
        return new ResponseEntity<>(responseGetAllItemsUnit, httpStatus);
    }

    @PostMapping("/v1/{idShoppinglistItem}/addItemUnitWP")
    public ResponseEntity<Void> addItemUnitWPToShoppinglistItem(@PathVariable("idShoppinglistItem") Long idShoppinglistItem, @RequestBody RequestAddItemUnitWP requestAddItemUnitWP) {
        logger.info("Creating a new item unit WP for the shoppinglist item with id: {}", idShoppinglistItem);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            shoppinglistItemService.addItemUnitWPToShoppinglistItem(idShoppinglistItem, requestAddItemUnitWP);
        }catch (ShoppinglistItemException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.ok(null);
    }


}

package es.franricodev.shopping_list_gestor_service.shoppinglistitem.controller;

import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.itemUnit.exception.ItemUnitException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetAllItemsUnit;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestAddItemUnitUnitaryPrice;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItemV2;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseGetAllItemUnitUpGroupedByPrice;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseItemUnitWpMetadata;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.messages.ShoppinglistItemMessagesError;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.messages.ShoppinglistItemMessagesSuccess;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.service.ShoppinglistItemService;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestAddItemUnitWP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = {"http://localhost:8100/", "http://192.168.18.7:9000/", "*"})
@RestController
@RequestMapping("/api/shoppinglistitem")
public class ShoppinglistItemControllerImpl implements ShoppinglistItemController {

    @Autowired
    private ShoppinglistItemService shoppinglistItemService;

    @Override
    public ResponseEntity<ResponseDeleteShoppinglistItem> deleteShoppinglistItem(Long idItem) {
        log.info("Delete the shoppinglist item with id: {}", idItem);
        ResponseDeleteShoppinglistItem responseDeleteShoppinglistItem = null;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            responseDeleteShoppinglistItem = shoppinglistItemService.deleteLogicShoppinglistItemById(idItem);
        } catch (ShoppinglistItemException shoppinglistItemException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseDeleteShoppinglistItem, httpStatus);
    }

    @Override
    public ResponseEntity<Void> addItemUnit(Long idShoppinglistItem, CreateItemUnitData request) {
        log.info("Creating a new item unit UP for the shoppinglist item with id: {}", idShoppinglistItem);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            shoppinglistItemService.addItemUnitToShoppinglistItem(request, idShoppinglistItem);
        } catch (ShoppinglistItemException |ItemUnitException | ShoppinglistException e) {
            httpStatus =  HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(httpStatus);
    }

    @Override
    public ResponseEntity<Void> removeItemUnit(@PathVariable("idItem") Long idItem, @PathVariable("idItemUnit") Long idItemUnit) {
        log.info("Removing the item unit with id: {} from the shoppinglist item with id: {}", idItem, idItemUnit);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            shoppinglistItemService.removeItemUnitFromShoppinglistItem(idItem, idItemUnit);
        } catch (ShoppinglistItemException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(httpStatus);
    }

    @Override
    public ResponseEntity<ResponseGetAllItemsUnit> getAllItemUnitsFromShoppinglistItem(@PathVariable("idShoppinglistItem") Long idShoppinglistItem) {
        log.info("Getting all the items units with calculate system UP of shoppinglist item with id: {}", idShoppinglistItem);
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


    @Override
    public ResponseEntity<Void> addItemUnitWPToShoppinglistItem(@PathVariable("idShoppinglistItem") Long idShoppinglistItem, @RequestBody RequestAddItemUnitWP requestAddItemUnitWP) {
        log.info("Creating a new item unit WP for the shoppinglist item with id: {}", idShoppinglistItem);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            shoppinglistItemService.addItemUnitWPToShoppinglistItem(idShoppinglistItem, requestAddItemUnitWP);
        } catch (ShoppinglistItemException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.ok(null);
    }

    // TODO: ERROR AL EJECUTAR ESTE ENDPOINT
    @Override
    public ResponseEntity<ResponseGetAllItemUnitUpGroupedByPrice> getItemsUnitsUpGroupedByPrice(Long idShoppinglistItem) {
        log.info("Getting the information of all items units of the shoppinglist item with id {} grouped by his price", idShoppinglistItem);
        ResponseGetAllItemUnitUpGroupedByPrice response = null;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            response = shoppinglistItemService.getItemsUnitsUpGroupedByPrice(idShoppinglistItem);
        } catch (ShoppinglistItemException e) {
            log.error(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @Override
    public ResponseEntity<ResponseItemUnitWpMetadata> getItemsUnitsWpMetadata(Long idShoppinglistItem) {
        log.info("Getting the information of a item unit wp from the shoppinglist item with id: {}", idShoppinglistItem);
        ResponseItemUnitWpMetadata response = null;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            response = shoppinglistItemService.getItemUnitWpMetadata(idShoppinglistItem);
        } catch (ShoppinglistItemException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @Override
    public ResponseEntity<ResponseCreateShoppinglistItem> createShoppinglistItemMetadata(RequestCreateShoppinglistItemV2 requestCreateShoppinglistItem) {
        log.info("Creation of the shoppinglist item metadata");
        ResponseCreateShoppinglistItem response = null;
        HttpStatus httpStatus = HttpStatus.CREATED;
        try {
            response = shoppinglistItemService.createShoppinglistItemMetadata(requestCreateShoppinglistItem);
        } catch (ShoppinglistItemException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, httpStatus);
    }
}

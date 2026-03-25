package es.franricodev.shopping_list_gestor_service.shoppinglistitem.controller.impl;

import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.itemUnit.exception.ItemUnitException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetAllItemsUnit;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.constants.api.ApiShoppinglistItemConstants;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.controller.ShoppinglistItemController;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItemV2;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.*;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
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
@RequestMapping(ApiShoppinglistItemConstants.BASE_URL)
public class ShoppinglistItemControllerImpl implements ShoppinglistItemController {

    @Autowired
    private ShoppinglistItemService shoppinglistItemService;

    @Override
    public ResponseEntity<ResponseDeleteShoppinglistItem> deleteShoppinglistItem(Long idShoppinglistItem) {
        log.info("Delete the shoppinglist item with id: {}", idShoppinglistItem);
        ResponseDeleteShoppinglistItem responseDeleteShoppinglistItem = null;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            responseDeleteShoppinglistItem = shoppinglistItemService.deleteLogicShoppinglistItemById(idShoppinglistItem);
        } catch (ShoppinglistItemException shoppinglistItemException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseDeleteShoppinglistItem, httpStatus);
    }

    @Override
    public ResponseEntity<Void> addItemUnitUp(Long idShoppinglistItem, CreateItemUnitData request) {
        log.info("Creating a new item unit UP for the shoppinglist item with id: {}", idShoppinglistItem);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            shoppinglistItemService.addItemUnitUpToShoppinglistItem(request, idShoppinglistItem);
        } catch (ShoppinglistItemException |ItemUnitException | ShoppinglistException e) {
            httpStatus =  HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(httpStatus);
    }

    @Override
    public ResponseEntity<Void> removeItemUnit(Long idItem, Long idShoppinglistItem) {
        log.info("Removing the item unit with id: {} from the shoppinglist item with id: {}", idItem, idShoppinglistItem);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            shoppinglistItemService.removeItemUnitFromShoppinglistItem(idItem, idShoppinglistItem);
        } catch (ShoppinglistItemException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(httpStatus);
    }

    @Override
    public ResponseEntity<ResponseGetAllItemsUnit> getAllItemUnitsFromShoppinglistItem(Long idShoppinglistItem) {
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
    public ResponseEntity<Void> addItemUnitWPToShoppinglistItem(Long idShoppinglistItem, RequestAddItemUnitWP requestAddItemUnitWP) {
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
    // TODO: CREATE LOGIC
    @Override
    public ResponseEntity<ResponseGetItemUnitWpMetadata> getItemUnitWpMetadata(Long idShoppinglistItem) {
        // ResponseGetItemUnitWpMetadata response = new ResponseGetItemUnitWpMetadata(10.0, 10.0, 100.00);
        log.info("Getting the item unit metadata");
        ResponseGetItemUnitWpMetadata response = null;
        ResponseItemUnitWpMetadata result = null;
        try {
            result = shoppinglistItemService.getItemUnitWpMetadata(idShoppinglistItem);
            response = new ResponseGetItemUnitWpMetadata(result.getPriceKg(), result.getWeight(), result.getCalculatedPrice(), result.getIdItemUnitWp(), result.getIdItemUnit());
        } catch (ShoppinglistItemException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> updateShoppinglistItemCalcualatedPrice(Long idShoppinglistItem) {
        log.info("Update the calculated price of the shoppinglist item with id {} ", idShoppinglistItem);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            shoppinglistItemService.updateShoppinglistItemCalculatedPrice(idShoppinglistItem);
        } catch (ShoppinglistItemException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(httpStatus);
    }
}

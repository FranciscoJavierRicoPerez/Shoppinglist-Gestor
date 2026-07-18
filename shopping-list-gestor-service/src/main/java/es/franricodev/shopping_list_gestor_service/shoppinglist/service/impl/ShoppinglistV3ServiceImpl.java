package es.franricodev.shopping_list_gestor_service.shoppinglist.service.impl;

import es.franricodev.shopping_list_gestor_service.constants.GeneralConstants;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.ItemUnitDTO;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.messages.ErrorMessages;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetAllItemsUnit;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistExceptionV2;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglist.repository.ShoppinglistRepository;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistV3Service;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestUpdateShoppinglistItemItemUnitsUp;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseGetAllItemUnitUpGroupedByPrice;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseItemUnitWpMetadata;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.service.ShoppinglistItemService;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestAddItemUnitWP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ShoppinglistV3ServiceImpl implements ShoppinglistV3Service {

    /**
     * The shoppinglist repository
     */
    @Autowired
    private ShoppinglistRepository shoppinglistRepository;

    /**
     * The shoppinglist item service
     */
    @Autowired
    private ShoppinglistItemService shoppinglistItemService;

    /**
     * Logic delete of a shoppinglist item
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @return ResponseDeleteShoppinglistItem
     */
    @Override
    public ResponseDeleteShoppinglistItem deleteShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem) {
        log.info("Logic delete of the shoppinglist item : [{}]",idShoppinglistItem);
        isActive(idShoppinglist);
        ResponseDeleteShoppinglistItem response = shoppinglistItemService.deleteLogicShoppinglistItemById(idShoppinglistItem);
        updateShoppinglistInformation(idShoppinglist, idShoppinglistItem);
        return response;
    }

    /**
     * Add a item unit up to the shoppinglist item
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @param request
     */
    @Override
    public void addItemUnitUpToShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem, CreateItemUnitData request) {
        log.info("Adding new ITEM_UNIT_UP to SHOPPINGLIST_ITEM with ID: [{}]", idShoppinglistItem);
        isActive(idShoppinglist);
        shoppinglistItemService.addItemUnitUpToShoppinglistItem(request, idShoppinglistItem);
        updateShoppinglistInformation(idShoppinglist, idShoppinglistItem);
    }

    /**
     * Delete item unit from shoppinglist item
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @param idItemUnit
     */
    @Override
    public void deleteItemUnitFromShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem, Long idItemUnit) {
        log.info("Remove ITEM_UNIT with id: [{}] from the SHOPPINGLIST_ITEM with id: [{}] in the SHOPPINGLIST with id: [{}]", idItemUnit, idShoppinglistItem, idShoppinglist);
        isActive(idShoppinglist);
        shoppinglistItemService.removeItemUnitFromShoppinglistItem(idShoppinglistItem, idItemUnit);
        updateShoppinglistInformation(idShoppinglist, idShoppinglistItem);
    }

    /**
     * Gives all the items units from the shoppinglist item
     *
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @return
     */
    @Override
    public ResponseGetAllItemsUnit getAllItemUnitsFromShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem) {
        log.info("Get all ITEMS UNITS from the SHOPPINGLIST ITEM with id: [{}] from the SHOPPINGLIST with id: [{}]", idShoppinglistItem, idShoppinglist);
        isActive(idShoppinglist);
        List<ItemUnitDTO> itemUnitDTOList = shoppinglistItemService.getAllItemUnitsFromShoppinglistItem(idShoppinglistItem);
        ResponseGetAllItemsUnit responseGetAllItemsUnit = new ResponseGetAllItemsUnit();
        responseGetAllItemsUnit.setItemUnitList(itemUnitDTOList);
        responseGetAllItemsUnit.setMessage("ITEMS UNITS OBTENIDOS CON EXITO");
        return responseGetAllItemsUnit;
    }

    /**
     * Add a new item unit wp to the shoppinglist item
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @param request
     */
    @Override
    public void addItemUnitWpToShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem, RequestAddItemUnitWP request) {
        log.info("Add ITEM UNIT WP to the SHOPPINGLIST_ITEM with id: [{}] from the SHOPPINGLIST with id: [{}]", idShoppinglistItem, idShoppinglistItem);
        isActive(idShoppinglist);
        shoppinglistItemService.addItemUnitWPToShoppinglistItem(idShoppinglistItem, request);
        updateShoppinglistInformation(idShoppinglist, idShoppinglistItem);
    }

    /**
     * Get items units up grouped by price
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @return
     */
    @Override
    public ResponseGetAllItemUnitUpGroupedByPrice getItemsUnitGroupedByPrice(Long idShoppinglist, Long idShoppinglistItem) {
        log.info("Get all items units up grouped by price from SHOPPINGLIST_ITEM with id: [{}] from the SHOPPINGLIST with id: [{}]", idShoppinglistItem, idShoppinglist);
        isActive(idShoppinglist);
        return shoppinglistItemService.getItemsUnitsUpGroupedByPrice(idShoppinglistItem);
    }

    /**
     * Get item units Wp metadata
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @return
     */
    @Override
    public ResponseItemUnitWpMetadata getItemUnitsWpMetadata(Long idShoppinglist, Long idShoppinglistItem) {
        log.info("Get items units wp metadata from SHOPPINGLIST_ITEM with id: [{}] from the SHOPPINGLIST with id: [{}]", idShoppinglistItem, idShoppinglist);
        isActive(idShoppinglist);
        return shoppinglistItemService.getItemUnitWpMetadata(idShoppinglistItem);
    }

    /**
     * Update item unit up data from a shoppinglist item
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @param request
     */
    @Override
    public void updateItemUnitUpDataFromShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem, RequestUpdateShoppinglistItemItemUnitsUp request) {
        log.info("Update item unit up data from SHOPPINGLIST_ITEM with id: [{}] from the SHOPPINGLIST with id: [{}]", idShoppinglistItem, idShoppinglist);
        isActive(idShoppinglist);
        shoppinglistItemService.updateShoppinglistItemUpItemsUnitData(idShoppinglistItem, request);
        updateShoppinglistInformation(idShoppinglist, idShoppinglistItem);
    }

    /**
     * Update the shoppinglist information, recalculating the data
     * @param idShoppinglist
     * @param idShoppinglistItem
     */
    @Override
    public void updateShoppinglistInformation(Long idShoppinglist, Long idShoppinglistItem) {
        log.info("Recalculating the data of the shoppinglist with id [{}]", idShoppinglist);
        Shoppinglist shoppinglist = getShoppinglistById(idShoppinglist);
        shoppinglistItemService.updateShoppinglistItemCalculatedPrice(idShoppinglistItem);
        Double recalculatedTotalPrice = 0D;
        for (ShoppinglistItem shoppinglistItem : shoppinglist.getItems()) {
            recalculatedTotalPrice += shoppinglistItem.getCalculatedPrice();
        }
        shoppinglist.setTotalPrice(recalculatedTotalPrice);
        shoppinglistRepository.save(shoppinglist);
    }

    /**
     * Verify if a shoppinglist is active or not
     * @param idShoppinglist
     * @return boolean
     */
    private void isActive(Long idShoppinglist) {
        log.info("Verifying if the shoppinglist [{}] is active or not", idShoppinglist);
        Shoppinglist shoppinglist = getShoppinglistById(idShoppinglist);
        if (!shoppinglist.getIsActive()) {
            log.info("The shoppinglist [{}] is not active", idShoppinglist);
            throw new ShoppinglistExceptionV2(
                    ErrorMessages.ERR_SHOPPINGLIST_IS_NOT_ACTIVE,
                    GeneralConstants.HTTP_409,
                    GeneralConstants.DESC_409,
                    HttpStatus.CONFLICT
            );
        }
        log.info("The shoppinglist [{}] is active", idShoppinglist);
    }

    /**
     * Get a shoppinglist by his id
     * @param idShoppinglist
     * @return Shoppinglist
     */
    private Shoppinglist getShoppinglistById(Long idShoppinglist) {
        log.info("Getting the shoppinglist with id [{}]", idShoppinglist);
        return shoppinglistRepository.findByIdAndInfoBlockFalse(idShoppinglist).orElseThrow(
                () -> new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND)
        );
    }


}
